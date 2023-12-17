package com.sales.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Exception thrown when an error occurs during the calculation of taxes. This
 * could happen if the tax calculation logic encounters invalid data, such as
 * negative item prices.
 */
public class TaxCalculationException extends RuntimeException {
	private static final Logger logger = LogManager.getLogger(TaxCalculationException.class);

	/**
	 * Constructs a new TaxCalculationException with the specified detail message.
	 * 
	 * @param message the detail message. The detail message is saved for later
	 *                retrieval by the Throwable.getMessage() method.
	 */
	public TaxCalculationException(String message) {
		super(message);
		logger.error("TaxCalculationException: {}", message);
	}
}