package com.pwc.lrc.controller;

import com.pwc.lrc.dto.LandRouteResponse;
import com.pwc.lrc.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/routing")
@RequiredArgsConstructor
public class LandRouteController {
    private final RouteService routeService;

    @GetMapping("/{origin}/{destination}")
    public LandRouteResponse getRoute(
            @PathVariable("origin") String origin,
            @PathVariable("destination") String destination
    ) {
        return routeService.findRoute(origin, destination);
    }
}
