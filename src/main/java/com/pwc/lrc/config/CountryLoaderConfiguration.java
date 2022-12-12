package com.pwc.lrc.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pwc.lrc.mapper.CountryMapper;
import com.pwc.lrc.service.client.CountryClient;
import com.pwc.lrc.service.loader.RemoteCountryLoader;
import com.pwc.lrc.service.loader.ResourceCountryLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

@Configuration
public class CountryLoaderConfiguration {
    @Configuration
    @ConditionalOnProperty(name = "route.resource-location", havingValue = "local", matchIfMissing = true)
    public static class ResourceCountryLoaderConfiguration {
        @Bean(initMethod = "init")
        public ResourceCountryLoader resourceCountryLoader(
                @Value("${route.local.uri}") String filePath,
                CountryMapper countryMapper,
                ObjectMapper objectMapper
        ) {
            return new ResourceCountryLoader(filePath, countryMapper, objectMapper);
        }
    }

    @Configuration
    @EnableFeignClients(clients = {CountryClient.class})
    @ConditionalOnProperty(name = "route.resource-location", havingValue = "remote")
    public static class RemoteCountryLoaderConfiguration {
        @Bean(initMethod = "init")
        public RemoteCountryLoader remoteCountryLoader(
                CountryClient countryClient,
                CountryMapper countryMapper
        ) {
            return new RemoteCountryLoader(countryClient, countryMapper);
        }

        @Bean
        public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            converter.setSupportedMediaTypes(List.of(
                    MediaType.TEXT_PLAIN
            ));
            return converter;
        }
    }
}
