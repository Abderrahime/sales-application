package com.sales.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.dto.ItemDto;
import com.sales.exception.ReceiptGenerationException;

/**
 * Service class for generating receipts. Computes and prints receipts for a
 * list of items.
 */
@Service
public class ReceiptService {

	private final TaxService taxService;

	@Autowired
	public ReceiptService(TaxService taxService) {
		this.taxService = taxService;
	}

	/**
	 * Prints a receipt for a list of items.
	 *
	 * @param itemDtos the list of item DTOs for which to generate the receipt
	 * @throws ReceiptGenerationException if any item has negative price or quantity
	 */
	public void printReceipt(List<ItemDto> itemDtos) {
		validateItems(itemDtos);

		double totalTax = 0.0;
		double totalPrice = 0.0;

		for (ItemDto itemDto : itemDtos) {
			double tax = taxService.calculateTaxFromDto(itemDto);
			totalTax += tax;
			double priceWithTax = itemDto.getPrice() + tax;
			totalPrice += priceWithTax;

			System.out.println(itemDto.getName() + ": " + formatPrice(priceWithTax));
		}

		System.out.println("Sales Taxes: " + formatPrice(totalTax));
		System.out.println("Total: " + formatPrice(totalPrice));
	}

	private void validateItems(List<ItemDto> itemDtos) {
		for (ItemDto item : itemDtos) {
			if (item.getPrice() < 0 || item.getQuantity() < 0) {
				throw new ReceiptGenerationException("Item cannot have negative price or quantity.");
			}
		}
	}

	private String formatPrice(double price) {
		return String.format("%.2f", price);
	}
}
