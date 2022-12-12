package com.pwc.lrc.config;

import com.pwc.lrc.service.LandRouteService;
import com.pwc.lrc.service.algorithm.PathSearchAlgorithm;
import com.pwc.lrc.service.RouteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {
    @Bean
    public RouteService landRouteService(PathSearchAlgorithm pathSearchAlgorithm) {
        return new LandRouteService(pathSearchAlgorithm);
    }
}
