package com.sales.mapper;

import org.mapstruct.Mapper;
import com.sales.dto.ItemDto;
import com.sales.model.Item;
/**
 * MapStruct mapper for converting between Item entities and ItemDto objects.
 */
@Mapper(componentModel = "spring")
public interface ItemMapper {
    ItemDto itemToItemDto(Item item);
    Item itemDtoToItem(ItemDto itemDto);
}