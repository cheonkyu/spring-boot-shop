package app.shop.domain.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

import app.shop.domain.entity.Item;

@Mapper
public interface ItemMapper {
    Item selectById(String no);
    int insertBulkItem(Item item);
}