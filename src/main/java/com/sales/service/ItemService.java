package com.sales.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.dto.ItemDto;
import com.sales.exception.InvalidItemException;
import com.sales.exception.ItemNotFoundException;
import com.sales.mapper.ItemMapper;
import com.sales.model.Item;
import com.sales.repository.ItemRepository;

/**
 * Service class for managing items. Handles operations such as saving,
 * retrieving, and finding items.
 */
@Service
public class ItemService {

	private static final Logger logger = LogManager.getLogger(ItemService.class);

	private final ItemRepository itemRepository;
	private final ItemMapper itemMapper;

	@Autowired
	public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
		this.itemRepository = itemRepository;
		this.itemMapper = itemMapper;
	}

	/**
	 * Saves an item to the database.
	 *
	 * @param itemDto the item DTO to be saved
	 * @return the saved item as a DTO
	 * @throws InvalidItemException if the item data is invalid
	 */
	public ItemDto saveItem(ItemDto itemDto) {

		if (itemDto.getPrice() < 0) {
			throw new InvalidItemException("Price cannot be negative.");
		}
		try {
			logger.info("Saving item: {}", itemDto.getName());
			Item item = itemMapper.itemDtoToItem(itemDto);
			Item savedItem = itemRepository.save(item);
			logger.info("Item saved successfully: {}", itemDto.getName());
			return itemMapper.itemToItemDto(savedItem);
		} catch (Exception e) {
			logger.error("Error saving item: {}", itemDto.getName(), e);
			throw e;
		}
	}

	/**
	 * Retrieves all items from the database.
	 *
	 * @return a list of all items as DTOs
	 */
	public List<ItemDto> getAllItems() {
		logger.info("Fetching all items");
		return itemRepository.findAll().stream().map(itemMapper::itemToItemDto).collect(Collectors.toList());
	}

	/**
	 * Retrieves an item by its ID.
	 *
	 * @param id the ID of the item to be retrieved
	 * @return the found item as a DTO
	 * @throws ItemNotFoundException if the item is not found
	 */
	public ItemDto getItemById(Long id) {
		Item item = itemRepository.findById(id)
				.orElseThrow(() -> new ItemNotFoundException("Item not found with id: " + id));
		return itemMapper.itemToItemDto(item);
	}

}
