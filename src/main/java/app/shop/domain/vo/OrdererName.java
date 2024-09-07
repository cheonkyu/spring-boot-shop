package app.shop.domain.vo;

import jakarta.persistence.Embeddable;
import app.shop.utils.validation.Validator;

@Embeddable
public record OrdererName (
    String name
) {

    public OrdererName {
        Validator.require(name != null && !name.isBlank(), () -> "성함을 확인해주세요.");
    }

    public static OrdererName of(String name) {
        return new OrdererName(name);
    }
}
