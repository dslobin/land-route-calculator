package com.pwc.lrc.config;

import com.pwc.lrc.service.algorithm.BreadthFirstSearchAlgorithm;
import com.pwc.lrc.service.loader.CountryLoader;
import com.pwc.lrc.service.algorithm.PathSearchAlgorithm;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PathSearchAlgorithmConfiguration {
    @Configuration
    @ConditionalOnProperty(name = "route.algorithm", havingValue = "breadth-first-search", matchIfMissing = true)
    public static class BtsPathSearchAlgorithmConfiguration {
        @Bean
        public PathSearchAlgorithm pathSearchAlgorithm(CountryLoader countryLoader) {
            return new BreadthFirstSearchAlgorithm(countryLoader);
        }
    }
}
