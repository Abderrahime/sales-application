package com.sales.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Exception thrown when an item is not found.
 */
public class ItemNotFoundException extends RuntimeException {

	private static final Logger logger = LogManager.getLogger(ItemNotFoundException.class);

	public ItemNotFoundException(String message) {
		super(message);
		logger.error("ItemNotFoundException: {}", message);
	}
}
