package app.shop.domain.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

import app.shop.domain.entity.Order;
import app.shop.domain.entity.OrderItem;
import app.shop.domain.repository.mapper.dto.ItemBulkDto;
import app.shop.domain.repository.mapper.dto.OrdererBulkDto;

@Mapper
public interface BulkInsertMapper {
    int insertItem(ItemBulkDto item);
    int insertOrderer(OrdererBulkDto item);
    int insertOrderItem(OrderItem item);
    int insertOrder(Order item);
}