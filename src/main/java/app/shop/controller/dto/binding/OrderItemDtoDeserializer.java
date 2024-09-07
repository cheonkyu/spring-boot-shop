package app.shop.controller.dto.binding;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import app.shop.domain.vo.OrderItemCount;
import app.shop.domain.vo.ItemName;
import app.shop.controller.dto.OrderItemDto;
 
public class OrderItemDtoDeserializer extends JsonDeserializer<OrderItemDto> {

    @Override
    public OrderItemDto deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, IllegalArgumentException {
        JsonNode node = p.getCodec().readTree(p);
        JsonNode nameValue = node.findValue("name");
        final ItemName name = ItemName.of(nameValue.asText());
        JsonNode orderItemCountValue = node.findValue("count");
        final OrderItemCount count = OrderItemCount.of(orderItemCountValue.asLong());
        return OrderItemDto.of(name, count);
    }
}