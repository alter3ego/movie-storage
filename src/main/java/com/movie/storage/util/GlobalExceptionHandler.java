package com.movie.storage.util;

import com.movie.storage.entity.AppError;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
@Order(2)
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<AppError> catchException(Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new AppError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ooops, we are working on it!"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
