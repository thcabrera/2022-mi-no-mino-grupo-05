package domain.services.calculoDistancia.adapters;

import domain.Direccion;
import domain.services.calculoDistancia.entities.Distancia;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ServicioDistanciaRetrofitAdapter implements DistanciaServiceAdapter{
    private static String urlApi = "https://ddstpa.com.ar/api/";
    private String token;
    private String apiVersion = "application/json";
    private Retrofit retrofit;

    public ServicioDistanciaRetrofitAdapter(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.token = "Bearer " + "/UkualY+RF4imy5QVe6/lcjvMDuQrJQti0nz0RFFtI4=";
    }

    public Distancia calculoDistancia(Direccion inicio, Direccion fin) throws IOException {
        DistanciaService distanciaService = this.retrofit.create(DistanciaService.class);
        Call<Distancia> requestDistancia = distanciaService.calculoDistancia(token, apiVersion, inicio.getLocalidad().getId(),
                inicio.getCalle(),
                inicio.getAltura(),
                fin.getLocalidad().getId(),
                fin.getCalle(),
                fin.getAltura());
        Response<Distancia> responseDistancia = requestDistancia.execute();
        return responseDistancia.body();
    }
}
