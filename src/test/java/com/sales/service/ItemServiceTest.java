package com.sales.service;

import com.sales.dto.ItemDto;
import com.sales.exception.InvalidItemException;
import com.sales.exception.ItemNotFoundException;
import com.sales.mapper.ItemMapper;
import com.sales.model.Item;
import com.sales.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
/**
 * Unit tests for ItemService.
 * Tests cover saving items, retrieving all items, and finding items by ID.
 */
class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ItemMapper itemMapper;

    private ItemService itemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        itemService = new ItemService(itemRepository, itemMapper);
    }

    @Test
    void saveItem_Success() {
        ItemDto itemDto = new ItemDto("test item", 10.00, 1, false, false);
        Item item = new Item(1, "test item", 10.00, 1, false, false);

        when(itemMapper.itemDtoToItem(itemDto)).thenReturn(item);
        when(itemRepository.save(item)).thenReturn(item);
        when(itemMapper.itemToItemDto(item)).thenReturn(itemDto);

        ItemDto savedItemDto = itemService.saveItem(itemDto);

        assertNotNull(savedItemDto);
        assertEquals("test item", savedItemDto.getName());
    }
    
    @Test
    void saveItem_InvalidItemData() {
        ItemDto itemDto = new ItemDto("test item", -10.00, 1, false, false); // Invalid price

        assertThrows(InvalidItemException.class, () -> {
            itemService.saveItem(itemDto);
        });
    }

    @Test
    void getAllItems_Success() {
        Item item1 = new Item(1, "item1", 10.00, 1, false, false);
        Item item2 = new Item(2, "item2", 20.00, 1, false, true);
        List<Item> items = Arrays.asList(item1, item2);
        ItemDto itemDto1 = new ItemDto("item1", 10.00, 1, false, false);
        ItemDto itemDto2 = new ItemDto("item2", 20.00, 1, false, true);

        when(itemRepository.findAll()).thenReturn(items);
        when(itemMapper.itemToItemDto(item1)).thenReturn(itemDto1);
        when(itemMapper.itemToItemDto(item2)).thenReturn(itemDto2);

        List<ItemDto> retrievedItemDtos = itemService.getAllItems();

        assertFalse(retrievedItemDtos.isEmpty());
        assertEquals(2, retrievedItemDtos.size());
        assertEquals("item1", retrievedItemDtos.get(0).getName());
        assertEquals("item2", retrievedItemDtos.get(1).getName());
    }
    
    @Test
    void getItemById_ItemNotFound() {
        Long itemId = 1L;
        when(itemRepository.findById(itemId)).thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class, () -> {
            itemService.getItemById(itemId);
        });
    }
}
