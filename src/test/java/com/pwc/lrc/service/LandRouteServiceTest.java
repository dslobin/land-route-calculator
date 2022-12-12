package com.pwc.lrc.service;

import com.pwc.lrc.dto.LandRouteResponse;
import com.pwc.lrc.exception.ErrorCode;
import com.pwc.lrc.exception.ServiceException;
import com.pwc.lrc.service.algorithm.PathSearchAlgorithm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LandRouteServiceTest {
    @Mock
    private PathSearchAlgorithm algorithm;

    @InjectMocks
    private LandRouteService routeService;

    @Test
    void givenCountries_whenFindPath_thenReturnRoute() {
        List<String> route = List.of("ORIGIN", "DESTINATION");
        when(algorithm.findPath(any(), any())).thenReturn(route);

        LandRouteResponse routeResponse = routeService.findRoute("ORIGIN", "DESTINATION");

        assertThat(routeResponse.getRoute()).isEqualTo(route);
        verify(algorithm, only()).findPath(any(), any());
    }

    @Test
    void givenCountries_whenFindPath_thenNoRoute() {
        when(algorithm.findPath(any(), any())).thenReturn(List.of());

        ServiceException exception = catchThrowableOfType(
                () -> routeService.findRoute("ORIGIN", "DESTINATION"),
                ServiceException.class
        );

        assertThat(exception).isInstanceOf(ServiceException.class);
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.PATH_NOT_FOUND.getHttpCode());
        assertThat(exception.getMessage()).isEqualTo(ErrorCode.PATH_NOT_FOUND.getReasonPhrase());
        verify(algorithm, only()).findPath(any(), any());
    }
}
