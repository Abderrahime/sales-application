package com.sales.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
/**
 * Data Transfer Object for Item.
 * Used to transfer item data between layers of the application.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private String name;
    private double price;
    private int quantity;
    private boolean isImported;
    private boolean isExempt;

}
