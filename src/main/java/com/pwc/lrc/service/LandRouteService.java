package com.pwc.lrc.service;

import com.pwc.lrc.dto.LandRouteResponse;
import com.pwc.lrc.exception.ErrorCode;
import com.pwc.lrc.exception.ServiceException;
import com.pwc.lrc.service.algorithm.PathSearchAlgorithm;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class LandRouteService implements RouteService {
    private final PathSearchAlgorithm pathSearchAlgorithm;

    @Override
    public LandRouteResponse findRoute(String origin, String destination) {
        List<String> path = pathSearchAlgorithm.findPath(origin, destination);
        if (path.isEmpty()) {
            throw new ServiceException(ErrorCode.PATH_NOT_FOUND.getReasonPhrase(), ErrorCode.PATH_NOT_FOUND.getHttpCode());
        }
        return LandRouteResponse.builder()
                .route(path)
                .build();
    }
}
