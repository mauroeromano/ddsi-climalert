package ar.edu.utn.ba.ddsi.climalert.models;

import java.time.LocalDateTime;

public record Alerta(
        ClimaActual climaActual,
        String mensaje,
        LocalDateTime fechaHoraGeneracion
) {
}