package com.pwc.lrc.mapper.impl;

import com.pwc.lrc.dto.LocalResourceCountryDto;
import com.pwc.lrc.dto.RemoteCountryResponse;
import com.pwc.lrc.mapper.CountryMapper;
import com.pwc.lrc.model.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryMapperImpl implements CountryMapper {
    @Override
    public Country toCountry(RemoteCountryResponse response) {
        if (response == null) {
            return null;
        }
        Country country = new Country();
        country.setName(response.getName());
        country.setBorders(response.getBorders());
        return country;
    }

    @Override
    public Country toCountry(LocalResourceCountryDto response) {
        if (response == null) {
            return null;
        }
        Country country = new Country();
        country.setName(response.getName());
        country.setBorders(response.getBorders());
        return country;
    }
}
