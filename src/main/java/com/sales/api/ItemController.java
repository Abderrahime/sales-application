package com.sales.api;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sales.dto.ItemDto;
import com.sales.service.ItemService;
import com.sales.service.ReceiptService;

/**
 * REST controller for managing items. Provides endpoints for adding items,
 * retrieving all items, and generating receipts.
 */
@RestController
@RequestMapping("/items")
public class ItemController {

	private static final Logger logger = LogManager.getLogger(ItemController.class);

	
	@Autowired
	private ItemService itemService;

	@Autowired
	private ReceiptService receiptService;

	/**
	 * Adds a new item.
	 * 
	 * @param itemDto the item DTO containing item data to be added
	 * @return the added item as a DTO
	 */
	@PostMapping
	public ItemDto addItem(@RequestBody ItemDto itemDto) {
		logger.info("Adding a new item: {}", itemDto.getName());
		return itemService.saveItem(itemDto);
	}

	/**
	 * Retrieves a list of all items.
	 * 
	 * @return a list of all items as DTOs
	 */
	@GetMapping
	public List<ItemDto> getAllItems() {
		logger.info("Retrieving all items");
		return itemService.getAllItems();
	}

	/**
	 * Generates and prints a receipt for all items.
	 */
	@GetMapping("/receipt")
	public void generateReceipt() {
		logger.info("Generating receipt for all items");
		List<ItemDto> itemDtos = itemService.getAllItems();
		receiptService.printReceipt(itemDtos);
	}
}