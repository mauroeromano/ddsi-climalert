package ar.edu.utn.ba.ddsi.climalert.services.impl;

import ar.edu.utn.ba.ddsi.climalert.models.ClimaActual;
import ar.edu.utn.ba.ddsi.climalert.repositories.ClimaRepository;
import ar.edu.utn.ba.ddsi.climalert.services.ClimaService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClimaServiceImpl implements ClimaService {

    private final ClimaRepository climaRepository;

    public ClimaServiceImpl(ClimaRepository climaRepository) {
        this.climaRepository = climaRepository;
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
        //TODO: Consumir WeatherAPI
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