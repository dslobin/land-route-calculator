package com.pwc.lrc.mapper;

import com.pwc.lrc.dto.LocalResourceCountryDto;
import com.pwc.lrc.dto.RemoteCountryResponse;
import com.pwc.lrc.model.Country;

public interface CountryMapper {
    Country toCountry(RemoteCountryResponse response);

    Country toCountry(LocalResourceCountryDto response);
}
