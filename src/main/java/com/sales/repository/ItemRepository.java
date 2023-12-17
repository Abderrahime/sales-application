package com.sales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sales.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
