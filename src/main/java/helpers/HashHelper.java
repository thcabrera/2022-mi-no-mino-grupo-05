package helpers;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class HashHelper {

    public static String hashear(String texto){
        return Hashing.sha256().hashString(texto, StandardCharsets.ISO_8859_1).toString();
    }

}
