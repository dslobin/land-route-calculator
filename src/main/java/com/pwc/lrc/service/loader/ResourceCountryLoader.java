package com.pwc.lrc.service.loader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pwc.lrc.dto.LocalResourceCountryDto;
import com.pwc.lrc.mapper.CountryMapper;
import com.pwc.lrc.model.Country;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class ResourceCountryLoader implements CountryLoader {
    private static final TypeReference<List<LocalResourceCountryDto>> TYPE_REFERENCE = new TypeReference<>() {
    };

    private final String filePath;
    private final CountryMapper countryMapper;
    private final ObjectMapper objectMapper;
    private List<Country> countries;

    @Override
    public List<Country> getCountries() {
        return countries;
    }

    public void init() throws IOException {
        log.info("Loading countries from resource file [{}]...", filePath);
        File file = ResourceUtils.getFile(filePath);
        countries = objectMapper.readValue(file, TYPE_REFERENCE).stream()
                .map(countryMapper::toCountry)
                .collect(Collectors.toList());
        log.info("Loading is complete. Loaded {} countries", countries.size());
    }
}
