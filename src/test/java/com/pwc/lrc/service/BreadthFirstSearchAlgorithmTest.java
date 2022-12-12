package com.pwc.lrc.service;

import com.pwc.lrc.exception.ErrorCode;
import com.pwc.lrc.exception.ServiceException;
import com.pwc.lrc.model.Country;
import com.pwc.lrc.service.algorithm.BreadthFirstSearchAlgorithm;
import com.pwc.lrc.service.loader.CountryLoader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BreadthFirstSearchAlgorithmTest {
    @Mock
    private CountryLoader loader;
    @InjectMocks
    private BreadthFirstSearchAlgorithm algorithm;

    @Test
    void givenUnknownCountryCode_whenFindPath_thenGetServiceException() {
        when(loader.getCountries()).thenReturn(List.of());

        ServiceException exception = catchThrowableOfType(
                () -> algorithm.findPath("ORIGIN", "DESTINATION"),
                ServiceException.class
        );

        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.UNKNOWN_COUNTRY.getHttpCode());
        assertThat(exception.getMessage()).isEqualTo(ErrorCode.UNKNOWN_COUNTRY.getReasonPhrase());
    }

    @Test
    void givenCountries_whenFindPath_thenNoRoute() {
        Country fromCountry = new Country("ORIGIN", List.of());
        Country toCountry = new Country("DESTINATION", List.of());
        List<String> expectedPath = List.of();

        when(loader.getCountries()).thenReturn(List.of(fromCountry, toCountry));

        assertThat(algorithm.findPath("ORIGIN", "DESTINATION")).isEqualTo(expectedPath);
    }
}
