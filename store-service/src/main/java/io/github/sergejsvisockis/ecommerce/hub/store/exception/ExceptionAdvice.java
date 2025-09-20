package io.github.sergejsvisockis.ecommerce.hub.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(StoreException.class)
    public ResponseEntity<ExceptionMessage> handleStoreException(StoreException e) {
        return ResponseEntity
                .badRequest()
                .body(new ExceptionMessage(
                        HttpStatus.BAD_REQUEST.value(),
                        e.getMessage())
                );
    }

}
