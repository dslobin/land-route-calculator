package com.pwc.lrc.controller.advice;

import com.pwc.lrc.dto.ErrorResponse;
import com.pwc.lrc.exception.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class LandRouteApiControllerAdvice {
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponse> handleServiceException(
            WebRequest request,
            ServiceException exception
    ) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(ZonedDateTime.now())
                .status(exception.getErrorCode())
                .message(exception.getMessage())
                .path(request.getDescription(false))
                .build();
        return ResponseEntity.status(exception.getErrorCode())
                .body(errorResponse);
    }
}
