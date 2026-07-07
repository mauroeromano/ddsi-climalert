package ar.edu.utn.ba.ddsi.climalert.services.impl;

import ar.edu.utn.ba.ddsi.climalert.models.Alerta;
import ar.edu.utn.ba.ddsi.climalert.models.ClimaActual;
import ar.edu.utn.ba.ddsi.climalert.services.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final String from;
    private final String[] recipients;

    public EmailServiceImpl(
            JavaMailSender mailSender,
            @Value("${climalert.mail.from}") String from,
            @Value("${climalert.mail.recipients}") String[] recipients
    ) {
        this.mailSender = mailSender;
        this.from = from;
        this.recipients = recipients;
    }

    @Override
    public void enviarAlerta(Alerta alerta) {
        SimpleMailMessage mensaje = new SimpleMailMessage();

        mensaje.setFrom(from);
        mensaje.setTo(recipients);
        mensaje.setSubject("Alerta climática - Climalert");
        mensaje.setText(generarCuerpo(alerta));

        mailSender.send(mensaje);
    }

    private String generarCuerpo(Alerta alerta) {
        ClimaActual clima = alerta.climaActual();

        return """
                Se detectaron condiciones climáticas críticas.

                Detalle del clima:

                Ubicación: %s
                Temperatura: %.2f °C
                Humedad: %d %%
                Condición: %s
                Fecha/hora de consulta: %s

                Detalle de la alerta:

                Mensaje: %s
                Fecha/hora de generación: %s
                """.formatted(
                clima.ubicacion(),
                clima.temperatura(),
                clima.humedad(),
                clima.condicion(),
                clima.fechaHoraConsulta(),
                alerta.mensaje(),
                alerta.fechaHoraGeneracion()
        );
    }
}