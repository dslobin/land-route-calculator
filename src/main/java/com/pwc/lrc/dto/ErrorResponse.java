package com.pwc.lrc.dto;

import lombok.*;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ErrorResponse {
    ZonedDateTime timestamp;
    Integer status;
    String message;
    String path;
}
