package domain.services.calculoDistancia.adapters;

import domain.services.calculoDistancia.entities.Distancia;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface DistanciaService {

    @GET("distancia")
    Call<Distancia> calculoDistancia(@Header("Authorization") String token,
                                     @Header("Accept") String apiVersion
            , @Query("localidadOrigenId") int localidadOrigenId
            , @Query("calleOrigen") String calleOrigen
            , @Query("alturaOrigen") int alturaOrigen
            , @Query("localidadDestinoId") int localidadDestinoId
            , @Query("calleDestino")  String calleDestino
            , @Query("alturaDestino") int alturaDestino
    );
}
