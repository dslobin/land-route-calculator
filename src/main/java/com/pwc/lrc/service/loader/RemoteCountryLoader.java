package com.pwc.lrc.service.loader;

import com.pwc.lrc.mapper.CountryMapper;
import com.pwc.lrc.model.Country;
import com.pwc.lrc.service.client.CountryClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class RemoteCountryLoader implements CountryLoader {
    private final CountryClient countryClient;
    private final CountryMapper countryMapper;
    private List<Country> countries;

    @Override
    public List<Country> getCountries() {
        return countries;
    }

    private void init() {
        log.info("Loading countries from remote resource file...");
        countries = countryClient.getCountries().stream()
                .map(countryMapper::toCountry)
                .collect(Collectors.toList());
        log.info("Loading is complete. Loaded {} countries", countries.size());
    }
}
