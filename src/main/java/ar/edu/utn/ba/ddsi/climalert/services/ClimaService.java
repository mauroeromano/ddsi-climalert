package ar.edu.utn.ba.ddsi.climalert.services;

import ar.edu.utn.ba.ddsi.climalert.models.ClimaActual;

import java.util.List;

public interface ClimaService {

    ClimaActual registrarMedicion(
            String ubicacion,
            double temperatura,
            int humedad,
            String condicion
    );

    void obtenerYRegistrarClimaActual();

    ClimaActual obtenerUltimo();

    List<ClimaActual> obtenerHistorial();
}