package ar.edu.utn.ba.ddsi.climalert.services;

import ar.edu.utn.ba.ddsi.climalert.models.ClimaActual;

import java.util.List;

public interface ClimaService {

    void obtenerYRegistrarClimaActual();

    ClimaActual obtenerUltimo();

    List<ClimaActual> obtenerHistorial();
}