package ar.edu.utn.ba.ddsi.climalert.services;

import ar.edu.utn.ba.ddsi.climalert.models.Alerta;

public interface EmailService {

    void enviarAlerta(Alerta alerta);
}