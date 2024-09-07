package app.shop.domain.vo;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@ExtendWith({MockitoExtension.class})
public class ItemNameTest {

    @Test
    @DisplayName("상품명 동등성")
    public void addressTest() {
        ItemName itemName = ItemName.of("아이폰12");
        ItemName itemName1 = ItemName.of("아이폰12");
        Assertions.assertEquals(itemName, itemName1);
    }

    @Test
    @DisplayName("상품명 유효성 체크")
    public void addressExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> ItemName.of(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> ItemName.of(""));
    }
}
