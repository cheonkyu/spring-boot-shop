package app.shop.utils.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ValidatorTest {

    @Test
    @DisplayName("코틀린 require 테스트 Fail")
    public void validatorRequireFailTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Validator.require(false, () -> "에러테스트");
        });
    }

    @Test
    @DisplayName("코틀린 check 테스트 Fail")
    public void validatorCheckFailTest() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            Validator.check(false, () -> "에러테스트");
        });
    }
}
