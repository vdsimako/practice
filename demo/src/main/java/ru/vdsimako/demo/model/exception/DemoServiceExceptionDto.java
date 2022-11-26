package ru.vdsimako.demo.model.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DemoServiceExceptionDto {
    private ExceptionMessage exceptionMessage;
    private String description;
}
