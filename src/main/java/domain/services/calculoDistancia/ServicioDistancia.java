package domain.services.calculoDistancia;

import domain.Direccion;
import domain.services.calculoDistancia.adapters.DistanciaServiceAdapter;
import domain.services.calculoDistancia.entities.Distancia;

import java.io.IOException;

public class ServicioDistancia {
    // SINGLETON me asegura que voy a crear una sola instancia de la clase
    private static ServicioDistancia instancia = null;
    private DistanciaServiceAdapter adapter;

    public ServicioDistancia() {
    }

    public void setAdapter(DistanciaServiceAdapter adapter) {
        this.adapter = adapter;
    }

    public static ServicioDistancia getInstancia() {
        if (instancia == null) {
            instancia = new ServicioDistancia();
        }
        return instancia;
    }


    public Distancia calculoDistancia(Direccion inicio, Direccion fin) throws IOException {
        return this.adapter.calculoDistancia( inicio, fin);
    }

    /*public Distancia calcular(Direccion direccionInicio, Direccion direccionFin) {

    }*/
}
