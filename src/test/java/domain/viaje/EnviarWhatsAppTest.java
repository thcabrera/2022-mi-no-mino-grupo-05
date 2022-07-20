package domain.viaje;

import okhttp3.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class EnviarWhatsAppTest {

    private String destinatario;
    private String link;
    OkHttpClient client = new OkHttpClient();

    @BeforeEach
    public void init(){
        destinatario =  "1111111111"; //A quien le quieres escribir.
        link = " https://www.huellaCarbono.org.ar/recomendaciones.html";

    }
    public EnviarWhatsAppTest() throws IOException {

    }

    @Test
    public void test() throws IOException {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "token=JATafg4422g0K54&to="+destinatario+"&link="+link);
        Request request = new Request.Builder()
                .url("https://api.ultramsg.com/instance19/messages/link")
                .post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();

        Response response = client.newCall(request).execute();
    }



}
