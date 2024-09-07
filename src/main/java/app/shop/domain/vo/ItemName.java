package app.shop.domain.vo;

import jakarta.persistence.Embeddable;
import app.shop.utils.validation.Validator;

@Embeddable
public record ItemName (
    String name
) {
    public ItemName {
        Validator.require(name != null && !name.isBlank(), () -> new IllegalArgumentException("성함을 확인해주세요."));
    }

    public static ItemName of(String name) {
        return new ItemName(name);
    }
}
