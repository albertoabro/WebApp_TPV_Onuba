package tfg.front.error;

import org.springframework.http.HttpStatus;

public class RestTemplateError extends RuntimeException{
    private HttpStatus statusCode;
    private String error;

    public RestTemplateError(HttpStatus statusCode, String error) {
        super(error);
        this.statusCode = statusCode;
        this.error=error;
    }
}
