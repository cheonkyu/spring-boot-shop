package app.shop.domain.vo;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@ExtendWith({MockitoExtension.class})
public class AddressTest {

    @Test
    @DisplayName("주소 동등성")
    public void addressTest() {
        Address address = Address.of("서울특별시 A동");
        Address address1 = Address.of("서울특별시 A동");
        Assertions.assertEquals(address, address1);
    }

    @Test
    @DisplayName("주소 유효성 체크")
    public void addressExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Address.of(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Address.of(""));
    }
}
