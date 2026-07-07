package ar.edu.utn.ba.ddsi.climalert.services.impl;

import ar.edu.utn.ba.ddsi.climalert.models.Alerta;
import ar.edu.utn.ba.ddsi.climalert.models.ClimaActual;
import ar.edu.utn.ba.ddsi.climalert.services.AlertaService;
import ar.edu.utn.ba.ddsi.climalert.services.ClimaService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AlertaServiceImpl implements AlertaService {

    private static final double TEMPERATURA_CRITICA = 35;
    private static final int HUMEDAD_CRITICA = 60;

    private final ClimaService climaService;

    private LocalDateTime fechaHoraUltimoClimaAlertado;

    public AlertaServiceImpl(ClimaService climaService) {
        this.climaService = climaService;
    }

    @Override
    public Alerta analizarUltimoClima() {
        ClimaActual ultimoClima = climaService.obtenerUltimo();

        if (ultimoClima == null) {
            return null;
        }

        boolean temperaturaCritica = ultimoClima.temperatura() > TEMPERATURA_CRITICA;
        boolean humedadCritica = ultimoClima.humedad() > HUMEDAD_CRITICA;

        if (temperaturaCritica && humedadCritica) {

            if (ultimoClima.fechaHoraConsulta().equals(fechaHoraUltimoClimaAlertado)) {
                return null;
            }

            fechaHoraUltimoClimaAlertado = ultimoClima.fechaHoraConsulta();

            return new Alerta(
                    ultimoClima,
                    "Condiciones climáticas críticas detectadas",
                    LocalDateTime.now()
            );
        }

        return null;
    }
}