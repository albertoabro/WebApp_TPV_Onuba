package tfg.front.error;

public class RestTemplateError extends RuntimeException{

    public RestTemplateError(String statusCode) {
        super(statusCode);
    }
}
