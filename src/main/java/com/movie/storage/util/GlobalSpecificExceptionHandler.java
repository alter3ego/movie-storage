package com.movie.storage.util;

import com.movie.storage.entity.AppError;
import com.movie.storage.entity.Type;
import com.movie.storage.exception.TypeParsingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.Objects;

@RestControllerAdvice
@Log4j2
@Order(1)
public class GlobalSpecificExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<AppError> catchConstraintViolationException(ConstraintViolationException  e) {
        log.warn(e.getMessage(), e);
        return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                Objects.requireNonNull(e.getConstraintViolations()
                        .stream()
                        .map(ConstraintViolation::getMessageTemplate)
                        .toList().toString())),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppError> catchMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn(e.getMessage(), e);
        return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<AppError> catchHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.warn(e.getMessage(), e);
        return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                "Incorrect input data"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({TypeParsingException.class})
    public ResponseEntity<AppError> catchEnumTypeParsingException(TypeParsingException e) {
        log.warn(e.getMessage(), e);
        return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                "Type movies must be one of: " +
                        Arrays.toString(Type.values())), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<AppError> catchNoHandlerFoundException(NoHandlerFoundException e) {
        log.warn(e.getMessage(), e);
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(),
                "Page not found"),
                HttpStatus.NOT_FOUND);
    }
}
