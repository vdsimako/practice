package ru.vdsimako.demo.model.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionMessage {
    EMAIL_FORMAT_MISMATCH(HttpStatus.BAD_REQUEST, "email format mismatch");

    private final HttpStatus httpStatus;

    private final String message;


    ExceptionMessage(HttpStatus httpStatus,
                     String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
