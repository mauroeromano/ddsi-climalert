package ar.edu.utn.ba.ddsi.climalert.repositories;

import ar.edu.utn.ba.ddsi.climalert.models.ClimaActual;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryClimaRepository implements ClimaRepository{

    private final List<ClimaActual> mediciones = new ArrayList<>();

    @Override
    public void guardar(ClimaActual climaActual){
        mediciones.add(climaActual);
    }

    @Override
    public ClimaActual obtenerUltimo() {
        if (mediciones.isEmpty()) {
            return null;
        }
        return mediciones.getLast();
    }

    @Override
    public List<ClimaActual> obtenerTodos() {
        return List.copyOf(mediciones);
    }
}