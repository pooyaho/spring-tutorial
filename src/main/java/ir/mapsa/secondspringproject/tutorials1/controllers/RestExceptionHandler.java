package ir.mapsa.secondspringproject.tutorials1.controllers;

import ir.mapsa.secondspringproject.tutorials1.exceptions.ServiceException;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@ControllerAdvice
public class RestExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);
    private Properties properties = new Properties();

    @PostConstruct
    public void init() {
        try {
            properties.load(new FileReader(
                    "/Users/pooya/projects/second-spring-project/src/main/resources/exceptions_fa_IR.properties",
                    StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleException(ServiceException exception) {
        LOGGER.error("Service exception occurred!", exception);
        ExceptionResponse value = new ExceptionResponse();
        value.setError(true);
        String property = properties.getProperty(exception.getErrorCode());
        if (property == null) {
            property = properties.getProperty("default");
        }
        value.setMessage(property);
        return ResponseEntity.badRequest().body(value);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException exception) {
        LOGGER.error("Validation exception occurred!", exception);
        ExceptionResponse value = new ExceptionResponse();
        value.setError(true);
        FieldError fieldError = exception.getBindingResult().getFieldError();
        String message =
                "Error in field : " + fieldError.getField() + " " + fieldError.getDefaultMessage();
        value.setMessage(message);

        return ResponseEntity.badRequest().body(value);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleException(RuntimeException exception) {
        LOGGER.error("Unhandled exception occurred!", exception);
        ExceptionResponse value = new ExceptionResponse();
        value.setError(true);
        value.setMessage(properties.getProperty("unknown"));
        exception.printStackTrace();
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