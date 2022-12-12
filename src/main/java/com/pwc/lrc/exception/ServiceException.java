package com.pwc.lrc.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {
    private final int errorCode;

    public ServiceException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
