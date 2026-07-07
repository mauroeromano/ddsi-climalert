package ar.edu.utn.ba.ddsi.climalert.services;

import ar.edu.utn.ba.ddsi.climalert.dtos.weatherapi.WeatherApiCurrentResponse;

public interface WeatherApiService {

    WeatherApiCurrentResponse obtenerClimaActual();
}