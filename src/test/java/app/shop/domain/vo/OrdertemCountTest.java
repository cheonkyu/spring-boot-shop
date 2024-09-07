package app.shop.domain.vo;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@ExtendWith({MockitoExtension.class})
public class OrdertemCountTest {

    @Test
    @DisplayName("상품명 동등성")
    public void ordererNameTest() {
        OrderItemCount itemName = OrderItemCount.of(1L);
        OrderItemCount itemName1 = OrderItemCount.of(1L);
        Assertions.assertEquals(itemName, itemName1);
    }

    @Test
    @DisplayName("상품명 유효성 체크")
    public void ordererNameExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> OrderItemCount.of(0L));
        Assertions.assertThrows(IllegalArgumentException.class, () -> OrderItemCount.of(null));
    }
}
