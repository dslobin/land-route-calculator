package com.pwc.lrc.service.client;

import com.pwc.lrc.dto.RemoteCountryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        value = "country-client",
        url = "${route.remote.uri}"
)
public interface CountryClient {
    @GetMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
    List<RemoteCountryResponse> getCountries();
}
