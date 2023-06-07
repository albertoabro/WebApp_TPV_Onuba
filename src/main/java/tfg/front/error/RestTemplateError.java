package tfg.front.error;

import org.springframework.http.HttpStatus;

public class RestTemplateError extends RuntimeException{

    public RestTemplateError(String statusCode) {
        super(statusCode);
    }
}
