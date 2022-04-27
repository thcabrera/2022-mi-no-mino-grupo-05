package seguridad;

import seguridad.exception.SeguridadException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorContrasenia {

    private Pattern pattern;
    private Matcher matcher;

    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";

//    Debe tener entre 8 y 40 caracteres.
//    Debe contener por lo menos un digito.
//    Debe contener por lo menos un caracter en minuscula.
//    Debe contener por lo menos un caracter en mayuscula
//    Debe contener por lo menos uno de los siguientes caracteres especiales [ @ # $ % ! . ].

    public ValidadorContrasenia() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    public boolean validadContrasenia(final String password) {

        boolean goodPass = esContrasenaBuena(password);

        matcher = pattern.matcher(password);

        boolean strongPass = matcher.matches();

        return goodPass && strongPass;

    }

    // Aca valido que NO este denetro el archivo de contreseña malas
    private boolean esContrasenaBuena(String contraseniaIngresada) {

        boolean contrasenaValida = true;

        String lineaArchivo;

        try {
            InputStream archivo = Thread.currentThread().getContextClassLoader().getResourceAsStream("PeoresContrasenias.txt");
            InputStreamReader ir = new InputStreamReader(archivo, "UTF-8");
            BufferedReader br = new BufferedReader(ir);

            while ((lineaArchivo = br.readLine()) != null) {

                if (lineaArchivo.equals(contraseniaIngresada)) {
                    contrasenaValida = false;
                    break;
                }

            }
        } catch (IOException e) {
            contrasenaValida = false;
            e.printStackTrace();
        }

        return contrasenaValida;
    }
}

