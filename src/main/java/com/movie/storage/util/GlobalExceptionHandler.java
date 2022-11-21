package com.movie.storage.util;

import com.movie.storage.entity.AppError;
import com.movie.storage.entity.Type;
import com.movie.storage.exception.TypeParsingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.Objects;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<AppError> catchMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> catchEnumTypeParsingException(TypeParsingException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                "Type movies must be one of: " +
                        Arrays.toString(Type.values())), HttpStatus.BAD_REQUEST);
    }
}
