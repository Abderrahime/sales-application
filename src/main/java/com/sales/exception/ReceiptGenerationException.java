package com.sales.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Exception thrown when an error occurs during the generation of a receipt.
 * This could happen due to invalid item data such as negative prices or
 * quantities.
 */
public class ReceiptGenerationException extends RuntimeException {

	private static final Logger logger = LogManager.getLogger(ReceiptGenerationException.class);

	/**
	 * Constructs a new ReceiptGenerationException with the specified detail
	 * message.
	 * 
	 * @param message the detail message. The detail message is saved for later
	 *                retrieval by the Throwable.getMessage() method.
	 */
	public ReceiptGenerationException(String message) {
		super(message);
		logger.error("ReceiptGenerationException: {}", message);
	}

}