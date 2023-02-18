package gr.codehub.sacchon.exception;

public class BadRequestParamException extends Exception{
    public BadRequestParamException() {
    }

    public BadRequestParamException(String message) {
        super(message);
    }

    public BadRequestParamException(String message, Throwable cause) {
        super(message, cause);
    }
}
