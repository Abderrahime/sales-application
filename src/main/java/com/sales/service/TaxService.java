package com.sales.service;

import org.springframework.stereotype.Service;

import com.sales.dto.ItemDto;
import com.sales.exception.TaxCalculationException;

/**
 * Service class for calculating taxes. Provides functionality to compute tax
 * based on item properties.
 */
@Service
public class TaxService {

	private static final double BASIC_TAX_RATE = 0.10; // 10% basic sales tax
	private static final double IMPORT_DUTY_RATE = 0.05; // 5% import duty

	/**
	 * Calculates the tax for a given item based on its properties. The tax is
	 * calculated as a sum of basic sales tax and import duty, if applicable.
	 *
	 * @param itemDto the DTO of the item for which the tax is to be calculated
	 * @return the calculated tax amount
	 * @throws TaxCalculationException if the item's price is negative
	 */
	public double calculateTaxFromDto(ItemDto itemDto) {
		if (itemDto.getPrice() < 0) {
			throw new TaxCalculationException("Price cannot be negative.");
		}

		double tax = 0.0;

		if (!itemDto.isExempt()) {
			tax += itemDto.getPrice() * BASIC_TAX_RATE;
		}

		if (itemDto.isImported()) {
			tax += itemDto.getPrice() * IMPORT_DUTY_RATE;
		}

		return roundOffTax(tax);
	}

	/**
	 * Rounds off the tax to the nearest 0.05.
	 *
	 * @param tax the tax amount to be rounded off
	 * @return the rounded tax amount
	 */
	private double roundOffTax(double tax) {
		return Math.ceil(tax * 20) / 20.0;
	}
}
