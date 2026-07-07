package ar.edu.utn.ba.ddsi.climalert.services.impl;

import ar.edu.utn.ba.ddsi.climalert.dtos.weatherapi.WeatherApiCurrentResponse;
import ar.edu.utn.ba.ddsi.climalert.services.WeatherApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class WeatherApiServiceImpl implements WeatherApiService {

    private final RestClient restClient;
    private final String apiKey;
    private final String location;

    public WeatherApiServiceImpl(
            @Value("${weatherapi.base-url}") String baseUrl,
            @Value("${weatherapi.key}") String apiKey,
            @Value("${weatherapi.location}") String location
    ) {
        this.restClient = RestClient.create(baseUrl);
        this.apiKey = apiKey;
        this.location = location;
    }

    @Override
    public WeatherApiCurrentResponse obtenerClimaActual() {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/current.json")
                        .queryParam("key", apiKey)
                        .queryParam("q", location)
                        .build()
                )
                .retrieve()
                .body(WeatherApiCurrentResponse.class);
    }
}