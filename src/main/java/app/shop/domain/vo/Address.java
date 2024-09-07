package app.shop.domain.vo;

import jakarta.persistence.Embeddable;
import app.shop.utils.validation.Validator;

@Embeddable
public record Address (
    String address
) {
    public Address {
        Validator.require(address != null && !address.isBlank(), () -> new IllegalArgumentException("주소명을 확인해주세요."));
    }
    
    public static Address of(String address) {
        return new Address(address);
    }
}
