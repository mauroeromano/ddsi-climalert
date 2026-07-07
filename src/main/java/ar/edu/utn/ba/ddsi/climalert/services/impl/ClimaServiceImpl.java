package ar.edu.utn.ba.ddsi.climalert.services.impl;

import ar.edu.utn.ba.ddsi.climalert.dtos.weatherapi.WeatherApiCurrentResponse;
import ar.edu.utn.ba.ddsi.climalert.models.ClimaActual;
import ar.edu.utn.ba.ddsi.climalert.repositories.ClimaRepository;
import ar.edu.utn.ba.ddsi.climalert.services.ClimaService;
import ar.edu.utn.ba.ddsi.climalert.services.WeatherApiService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClimaServiceImpl implements ClimaService {

    private final ClimaRepository climaRepository;
    private final WeatherApiService weatherApiService;

    public ClimaServiceImpl(
            ClimaRepository climaRepository,
            WeatherApiService weatherApiService
    ) {
        this.climaRepository = climaRepository;
        this.weatherApiService = weatherApiService;
    }

    @Override
    public ClimaActual registrarMedicion(
            String ubicacion,
            double temperatura,
            int humedad,
            String condicion
    ) {
        ClimaActual climaActual = new ClimaActual(
                ubicacion,
                temperatura,
                humedad,
                condicion,
                LocalDateTime.now()
        );

        climaRepository.guardar(climaActual);

        return climaActual;
    }

    @Override
    public void obtenerYRegistrarClimaActual() {
        WeatherApiCurrentResponse response = weatherApiService.obtenerClimaActual();

        ClimaActual climaActual = registrarMedicion(
                response.location().name(),
                response.current().tempC(),
                response.current().humidity(),
                response.current().condition().text()
        );

        System.out.println("Medición obtenida desde WeatherAPI:");
        System.out.println(climaActual);
    }

    @Override
    public ClimaActual obtenerUltimo() {
        return climaRepository.obtenerUltimo();
    }

    @Override
    public List<ClimaActual> obtenerHistorial() {
        return climaRepository.obtenerTodos();
    }
}