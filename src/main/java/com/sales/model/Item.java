package com.sales.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Item {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
    private String name;
    private double price;
    private int quantity;
    private boolean isImported;
    private boolean isExempt;

}
