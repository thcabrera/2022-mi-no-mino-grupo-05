package domain;

import domain.services.calculoDistancia.ServicioDistancia;
import domain.services.calculoDistancia.adapters.ServicioDistanciaRetrofitAdapter;
import domain.services.calculoDistancia.entities.Distancia;
import java.io.IOException;

public class EjemploDeUso {
    public static void main(String[] args) throws IOException {
        ServicioDistancia servicioDistancia = ServicioDistancia.getInstancia();
        servicioDistancia.setAdapter(new ServicioDistanciaRetrofitAdapter());

        // Datos de prueba
        Direccion origen = new Direccion("maipu", 100,null, null,   1 );
        Direccion destino = new Direccion("O'Higgins", 200,null, null, 457);

        Distancia distancia = servicioDistancia.calcularDistanciaTramo(origen, destino);
        //System.out.println(distancia);
        System.out.println("Distancia entre los siguientes puntos: ");
        System.out.println("Origen: Maipu al 100 localidad 1");
        System.out.println("Destino: O'Higgins al 200 localidad 457");

        System.out.println("Distancia: "+distancia.valor+" "+distancia.unidad);

    }
}
