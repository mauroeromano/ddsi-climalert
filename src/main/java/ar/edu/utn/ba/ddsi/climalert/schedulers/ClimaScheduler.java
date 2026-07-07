package ar.edu.utn.ba.ddsi.climalert.schedulers;

import ar.edu.utn.ba.ddsi.climalert.models.Alerta;
import ar.edu.utn.ba.ddsi.climalert.services.AlertaService;
import ar.edu.utn.ba.ddsi.climalert.services.ClimaService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClimaScheduler {

    private final ClimaService climaService;
    private final AlertaService alertaService;

    public ClimaScheduler(ClimaService climaService, AlertaService alertaService) {
        this.climaService = climaService;
        this.alertaService = alertaService;
    }

    @Scheduled(fixedRate = 300000)
    public void obtenerYGuardarClimaAutomaticamente() {
        climaService.obtenerYRegistrarClimaActual();
    }

    @Scheduled(fixedRate = 60000)
    public void analizarAlertasAutomaticamente() {
        Alerta alerta = alertaService.analizarUltimoClima();

        if (alerta != null) {
            System.out.println("Alerta generada automáticamente:");
            System.out.println(alerta);
        }
    }
}