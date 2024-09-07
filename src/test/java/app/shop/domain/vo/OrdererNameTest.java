package app.shop.domain.vo;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@ExtendWith({MockitoExtension.class})
public class OrdererNameTest {

    @Test
    @DisplayName("상품명 동등성")
    public void ordererNameTest() {
        OrdererName itemName = OrdererName.of("홍길동");
        OrdererName itemName1 = OrdererName.of("홍길동");
        Assertions.assertEquals(itemName, itemName1);
    }

    @Test
    @DisplayName("상품명 유효성 체크")
    public void ordererNameExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> OrdererName.of(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> OrdererName.of(""));
    }
}
