package app.shop.utils.excel;

public record Column(
    String fieldName,
    String key
) {
    public static Column of(String fieldName, String key) {
        return new Column(fieldName, key);
    }
}
