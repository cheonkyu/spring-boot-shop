package app.shop.utils.validation;

import java.util.function.Supplier;

public class Validator {
    public static void require(boolean condition, Supplier<String> supplier) throws IllegalArgumentException {
        if(supplier == null) {
            return;
        }
        if(!condition) {
            throw new IllegalArgumentException(supplier.get());
        }
    }

    public static void check(boolean condition, Supplier<String> supplier) throws IllegalStateException {
        if(supplier == null) {
            return;
        }
        if(!condition) {
            throw new IllegalStateException(supplier.get());
        }
    }
}
