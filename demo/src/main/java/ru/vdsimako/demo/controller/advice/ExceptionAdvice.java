package ru.vdsimako.demo.controller.advice;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.vdsimako.demo.model.exception.DemoServiceException;
import ru.vdsimako.demo.model.exception.DemoServiceExceptionDto;

@Log4j2
@ControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DemoServiceException.class)
    public ResponseEntity<DemoServiceExceptionDto> handleDemoServiceException(DemoServiceException ex) {
        log.error("Catch exception ex: {}", ex);

        DemoServiceExceptionDto demoServiceExceptionDto = DemoServiceExceptionDto.builder()
                .exceptionMessage(ex.getExceptionMessage())
                .description(ex.getExceptionMessage().getMessage())
                .build();

        return ResponseEntity
                .status(ex.getExceptionMessage().getHttpStatus())
                .body(demoServiceExceptionDto);
    }
}
