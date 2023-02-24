package ir.mapsa.secondspringproject.tutorials1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleException(ServiceException exception) {
        ExceptionResponse value = new ExceptionResponse();
        value.setError(true);
        switch (exception.getErrorCode()) {
            case "student-notfound":
                value.setMessage("دانشجو یافت نشد");
                break;
            case "database-exception":
                value.setMessage("خطای پایگاه داده ای");
                break;
            default:
                value.setMessage("خطای سیستمی");
                break;
        }

        return ResponseEntity.badRequest().body(value);
    }


    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleException(RuntimeException exception) {
        ExceptionResponse value = new ExceptionResponse();
        value.setError(true);
        value.setMessage("خطای ناشناخته");
        return ResponseEntity.badRequest().body(value);
    }
}

class ExceptionResponse {
    private boolean error;
    private String message;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}