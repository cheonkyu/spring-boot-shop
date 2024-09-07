package app.shop.domain.vo;

import jakarta.persistence.Embeddable;
import app.shop.utils.validation.Validator;

@Embeddable
public record OrderItemCount (
    Long count
) {
    public OrderItemCount {
        Validator.require(count != null && count >= 1L, () -> "주문개수를 확인해주세요.");
    }
    
    public static OrderItemCount of(Long count) {
        return new OrderItemCount(count);
    }
}
