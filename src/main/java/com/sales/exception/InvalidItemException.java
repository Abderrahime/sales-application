package com.sales.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Exception thrown when an invalid item is processed.
 */
public class InvalidItemException extends RuntimeException {
	private static final Logger logger = LogManager.getLogger(InvalidItemException.class);

	public InvalidItemException(String message) {
		super(message);
		logger.error("InvalidItemException: {}", message);
	}
}