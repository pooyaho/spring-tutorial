package ir.mapsa.secondspringproject.tutorials1.exceptions;

public class IdNotFoundException extends ServiceException {
    public IdNotFoundException(String errorCode) {
        super(errorCode);
    }

    public IdNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }

    public IdNotFoundException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

    public IdNotFoundException(Throwable cause, String errorCode) {
        super(cause, errorCode);
    }

    public IdNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode) {
        super(message, cause, enableSuppression, writableStackTrace, errorCode);
    }
}
