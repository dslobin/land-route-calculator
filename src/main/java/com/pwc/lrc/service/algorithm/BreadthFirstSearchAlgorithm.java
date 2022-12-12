package com.pwc.lrc.service.algorithm;

import com.pwc.lrc.exception.ErrorCode;
import com.pwc.lrc.exception.ServiceException;
import com.pwc.lrc.model.Country;
import com.pwc.lrc.service.loader.CountryLoader;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BreadthFirstSearchAlgorithm implements PathSearchAlgorithm {
    private final CountryLoader countryLoader;

    @Override
    public List<String> findPath(String origin, String destination) {
        Map<String, List<String>> countries = countryLoader.getCountries().stream()
                .collect(Collectors.toMap(Country::getName, Country::getBorders));
        if (countries.get(origin) == null || countries.get(destination) == null) {
            throw new ServiceException(ErrorCode.UNKNOWN_COUNTRY.getReasonPhrase(), ErrorCode.UNKNOWN_COUNTRY.getHttpCode());
        }
        return findDestination(countries, origin, destination);
    }

    private List<String> findDestination(
            Map<String, List<String>> countries,
            String from,
            String to
    ) {
        Set<String> visited = new HashSet<>();
        visited.add(from);

        Deque<String> deque = new LinkedList<>();
        deque.add(from);
        Map<String, String> routeMap = new HashMap<>();

        while (!deque.isEmpty()) {
            String node = deque.removeFirst();
            List<String> nodeCountryBorders = countries.get(node);
            for (String borderCountry : nodeCountryBorders) {
                if (visited.contains(borderCountry)) {
                    continue;
                }
                visited.add(borderCountry);
                routeMap.put(borderCountry, node);
                if (Objects.equals(borderCountry, to)) {
                    return extractRoute(routeMap, from, to);
                }
                deque.add(borderCountry);
            }
        }

        return List.of();
    }

    private List<String> extractRoute(
            Map<String, String> route,
            String origin,
            String destination
    ) {
        String extracted = destination;
        List<String> extractedRoutes = new ArrayList<>();
        extractedRoutes.add(extracted);
        while (!Objects.equals(origin, extracted)) {
            extracted = route.get(extracted);
            extractedRoutes.add(extracted);
        }
        Collections.reverse(extractedRoutes);
        return extractedRoutes;
    }
}
