package com.pwc.lrc.controller.advice;

import com.pwc.lrc.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(
            WebRequest request,
            MissingServletRequestParameterException exception
    ) {
        int httpStatus = HttpStatus.BAD_REQUEST.value();
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(ZonedDateTime.now())
                .status(httpStatus)
                .message(exception.getMessage())
                .path(request.getDescription(false))
                .build();
        return ResponseEntity.status(httpStatus)
                .body(errorResponse);
    }
}
