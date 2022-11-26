package ru.vdsimako.demo.model.exception;

import lombok.Getter;

@Getter
public class DemoServiceException extends RuntimeException {
    private ExceptionMessage exceptionMessage;

    public DemoServiceException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.getMessage());
        this.exceptionMessage = exceptionMessage;
    }
}
