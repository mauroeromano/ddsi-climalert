package ar.edu.utn.ba.ddsi.climalert.models;

import java.time.LocalDateTime;

public record ClimaActual (
    String ubicacion,
    double temperatura,
    int humedad,
    String condicion,
    LocalDateTime fechaHoraConsulta
){
}