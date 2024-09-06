package app.shop.core.response;

public class Response<T> {
    private int status; // HTTP 상태 코드
    private T data; // 실제 데이터
    private ResponseError error; // error

    public Response(int status, T data, ResponseError error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public ResponseError getError() {
        return error;
    }
}
