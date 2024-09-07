package app.shop.controller.dto;

public class BulkInsertOrderDto {
    public record Request(
        String itemId,
        String itemName,
        String orderCount,
        String ordererName,
        String ordererAddress
    ) {
        public static Request of(
            String itemId,
            String itemName,
            String orderCount,
            String ordererName,
            String ordererAddress
        ) {
            return new Request(itemId, itemName, orderCount, ordererName, ordererAddress);
        }
    }


    public record Response(Boolean isSuccess) {
        public Response(Boolean isSuccess) {
            this.isSuccess = isSuccess;
        }
    }

    public static Response response(Boolean isSuccess) {
        return new Response(true);
    }
}
