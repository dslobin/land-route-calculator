package com.pwc.lrc.controller;

import com.pwc.lrc.dto.ErrorResponse;
import com.pwc.lrc.dto.LandRouteResponse;
import com.pwc.lrc.exception.ErrorCode;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class LandRouteControllerIntegrationTest {
    private static final String LAND_ROUTE_PATH_URI = "/api/v1/routing";
    private static final String GET_ROUTE_PATH_URI = LAND_ROUTE_PATH_URI + "/{origin}/{destination}";

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void beforeEach() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void givenCountries_whenGetPath_thenPathFound() {
        List<String> expectedRoute = List.of("CZE", "AUT", "ITA");

        LandRouteResponse response = RestAssuredMockMvc
                .when()
                .get(GET_ROUTE_PATH_URI, "CZE", "ITA")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(LandRouteResponse.class);

        assertThat(response.getRoute()).isEqualTo(expectedRoute);
    }

    @Test
    void givenCountries_whenGetPath_thenPathNotFound() {
        ErrorResponse response = RestAssuredMockMvc
                .when()
                .get(GET_ROUTE_PATH_URI, "POL", "AUS")
                .then()
                .statusCode(ErrorCode.PATH_NOT_FOUND.getHttpCode())
                .extract()
                .as(ErrorResponse.class);

        assertThat(response.getMessage()).isEqualTo(ErrorCode.PATH_NOT_FOUND.getReasonPhrase());
    }

    @Test
    void givenNonExistentCountry_whenGetPath_thenReturnErrorResponse() {
        ErrorResponse response = RestAssuredMockMvc
                .when()
                .get(GET_ROUTE_PATH_URI, "CZE", "NON_EXISTENT_COUNTRY_CODE")
                .then()
                .statusCode(ErrorCode.UNKNOWN_COUNTRY.getHttpCode())
                .extract()
                .as(ErrorResponse.class);

        assertThat(response.getMessage()).isEqualTo(ErrorCode.UNKNOWN_COUNTRY.getReasonPhrase());
    }
}
