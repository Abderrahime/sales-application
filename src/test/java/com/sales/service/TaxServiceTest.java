package com.sales.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.sales.dto.ItemDto;
import com.sales.exception.TaxCalculationException;
/**
 * Unit tests for TaxService.
 * Tests cover tax calculation for different types of items.
 */
class TaxServiceTest {

    private final TaxService taxService = new TaxService();

    @Test
    void calculateTaxFromDto_ExemptItem() {
        ItemDto itemDto = new ItemDto("book", 12.49, 1, false, true);
        assertEquals(0.0, taxService.calculateTaxFromDto(itemDto), "Tax should be zero for exempt items");
    }

    @Test
    void calculateTaxFromDto_ImportedItem() {
        ItemDto itemDto = new ItemDto("imported perfume", 27.99, 1, true, false);
        double expectedTax = Math.ceil((27.99 * 0.15) * 20) / 20.0; // 15% tax, rounded
		assertEquals(expectedTax, taxService.calculateTaxFromDto(itemDto),
				"Tax calculation for imported items is incorrect");
	}
    @Test
    void calculateTaxFromDto_WithValidItem() {
        ItemDto itemDto = new ItemDto("item", 100.0, 1, false, false);
        assertDoesNotThrow(() -> taxService.calculateTaxFromDto(itemDto));
    }

    @Test
    void calculateTaxFromDto_WithNegativePrice() {
        ItemDto itemDto = new ItemDto("item", -100.0, 1, false, false);
        assertThrows(TaxCalculationException.class, () -> taxService.calculateTaxFromDto(itemDto));
    }
}
