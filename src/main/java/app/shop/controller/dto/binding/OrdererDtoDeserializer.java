package app.shop.controller.dto.binding;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import app.shop.controller.dto.OrdererDto;
import app.shop.domain.vo.Address;
import app.shop.domain.vo.OrdererName;

public class OrdererDtoDeserializer extends JsonDeserializer<OrdererDto> {

    @Override
    public OrdererDto deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, IllegalArgumentException {
        JsonNode node = p.getCodec().readTree(p);
        JsonNode nameValue = node.findValue("name");
        final OrdererName name = OrdererName.of(nameValue.asText());
        JsonNode addressValue = node.findValue("address");
        final Address address = Address.of(addressValue.asText());
        return OrdererDto.of(name, address);
    }
}