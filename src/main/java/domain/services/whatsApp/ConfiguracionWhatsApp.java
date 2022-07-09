package domain.services.whatsApp;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ConfiguracionWhatsApp {
    public static void main(String args[]) throws IOException {
        //////////////////////////////////////
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "token=JATafg4422g0K54&to=1157587710&body=WhatsApp API on UltraMsg.com works good&priority=10&referenceId=");
        Request request = new Request.Builder()
                .url("https://api.ultramsg.com/instance11608/messages/chat")
                .post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();
        Response response = client.newCall(request).execute();
//////////////////////////////////////
        System.out.println("===================");
        System.out.println(response.body().string());
    }
}
