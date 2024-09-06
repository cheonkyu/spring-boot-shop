package app.shop.core.response;

class ResponseError {
    private int code;
    private String message;

    public ResponseError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

