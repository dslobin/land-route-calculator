package com.pwc.lrc.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    PATH_NOT_FOUND("Failed to build a path", 400),
    UNKNOWN_COUNTRY("Failed to find country by code", 400);

    private final String reasonPhrase;
    private final int httpCode;
}
