package com.pwc.lrc.service;

import com.pwc.lrc.dto.LandRouteResponse;

public interface RouteService {
    LandRouteResponse findRoute(String origin, String destination);
}
