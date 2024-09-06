package app.shop.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


public class BulkInsertOrderDto {
    // @Getter
    // @Setter
    // @AllArgsConstructor
    // public class Request {
    //     private String itemId;
    //     private String itemName;
    //     private String orderCount;
    //     private String ordererName;
    //     private String ordererAddress;
    // } {
    //     public Request() {

    //     }
    // }

    public record Request(
        String itemId,
        String itemName,
        String orderCount,
        String ordererName,
        String ordererAddress
    ) {
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
