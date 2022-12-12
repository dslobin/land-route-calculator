package com.pwc.lrc.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LandRouteResponse {
    private List<String> route;
}
