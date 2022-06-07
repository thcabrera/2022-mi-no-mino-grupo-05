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
        //String calle, Integer altura, String provincia, String municipio, Integer localidad
        Direccion origen = new Direccion("maipu", 100,"", "",   1 );
        Direccion destino = new Direccion("O'Higgins", 200,"", "", 457);

        Distancia distancia = servicioDistancia.calculoDistancia(origen, destino);
        //System.out.println(distancia);
        System.out.println("Distancia entre los siguientes puntos: ");
        System.out.println("Origen: Maipu al 100 localidad 1");
        System.out.println("Destino: O'Higgins al 200 localidad 457");

        System.out.println("Distancia: "+distancia.valor+" "+distancia.unidad);

    }
}
