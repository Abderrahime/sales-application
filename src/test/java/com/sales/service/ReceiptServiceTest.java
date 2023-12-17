package com.sales.service;

import com.sales.dto.ItemDto;
import com.sales.exception.ReceiptGenerationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
/**
 * Unit tests for ReceiptService.
 * Tests cover successful receipt generation and exception scenarios.
 */
class ReceiptServiceTest {

    @Mock
    private TaxService taxService;

    private ReceiptService receiptService;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        receiptService = new ReceiptService(taxService);
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void printReceipt_Success() {
        List<ItemDto> itemDtos = Arrays.asList(
            new ItemDto("book", 12.49, 1, false, true),
            new ItemDto("music CD", 14.99, 1, false, false)
        );
        when(taxService.calculateTaxFromDto(any(ItemDto.class))).thenReturn(1.50);

        receiptService.printReceipt(itemDtos);

        String receiptOutput = outContent.toString();
        assertTrue(receiptOutput.contains("book: 13.99"));
        assertTrue(receiptOutput.contains("music CD: 16.49"));
        assertTrue(receiptOutput.contains("Sales Taxes: 3.00"));
        assertTrue(receiptOutput.contains("Total: 30.48"));
    }
    
    @Test
    void printReceipt_WithInvalidItemData_ThrowsException() {
        List<ItemDto> itemDtos = Arrays.asList(
            new ItemDto("invalid item", -10.00, 1, false, false) // Invalid price
        );

        assertThrows(ReceiptGenerationException.class, () -> {
            receiptService.printReceipt(itemDtos);
        });
    }
}
