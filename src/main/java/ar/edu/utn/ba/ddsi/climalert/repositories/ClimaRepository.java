package ar.edu.utn.ba.ddsi.climalert.repositories;

import ar.edu.utn.ba.ddsi.climalert.models.ClimaActual;

import java.util.List;

public interface ClimaRepository {

    void guardar(ClimaActual climaActual);

    ClimaActual obtenerUltimo();

    List<ClimaActual> obtenerTodos();
}