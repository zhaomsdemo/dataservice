package com.zjh.dataservice.configuration;

import com.zjh.dataservice.error.ErrorResponse;
import com.zjh.dataservice.exception.DataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseBody
    public ErrorResponse handleNotFoundException(){
        return ErrorResponse.builder()
                .code(-1).status(HttpStatus.NOT_FOUND.value()).message("Data not found")
                .build();
    }
}
