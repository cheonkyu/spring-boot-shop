package app.shop.utils.validation;

import java.util.function.Supplier;

public class Validator {
    public static void require(boolean condition, Supplier<IllegalArgumentException> supplier) throws IllegalArgumentException {
        if(supplier == null) {
            return;
        }
        if(!condition) {
            throw supplier.get();
        }
    }

    public static void check(boolean condition, Supplier<IllegalStateException> supplier) throws IllegalStateException {
        if(supplier == null) {
            return;
        }
        if(!condition) {
            throw supplier.get();
        }
    }
}
