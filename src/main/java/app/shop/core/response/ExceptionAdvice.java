package app.shop.core.response;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionAdvice {

    @Value("${spring.exception.debug}")
    private Boolean isDebug;

    @ExceptionHandler(Exception.class)
    public Response<Object> handleException(Exception e, HttpServletRequest request) {
        return new Response<>(
            500,
            null,
            new ResponseError(
                500,
                e.getMessage()
                // isDebug
                //     ? e.getLocalizedMessage()
                //     : "잠시 후에 다시 s시도해주세요."
            )
        );
    }
}