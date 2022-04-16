package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seguridad.ValidadorContrasenia;

import java.io.BufferedReader;
import java.io.FileReader;


public class ValidadorContraseniaTest {

    private String passIncorrecta1, passIncorrecta2, passIncorrecta3, passIncorrecta4, passIncorrecta5;
    private String passCorrecta1, passCorrecta2, passCorrecta3;
    private String peorPass1;
    private ValidadorContrasenia validadorContrasenia;

    @BeforeEach
    void setupThis(){

        // Simulo el ingreso de contraseñas erronaeas
        passIncorrecta1 = "T@m!";				//Es menor de 8 caracteres
        passIncorrecta2 = "diseniods!";			//No contiene digitos ni mayusculas
        passIncorrecta3 = " abcdFg45*";			//No se permiten espaciones
        passIncorrecta4 = "t@m!abcD#AX";		//No contiene digito
        passIncorrecta5 = "ABCASWF2!";			//No contiene minusculas

        // Simulo el ingreso de contraseñas correctas
        passCorrecta1 = "t@m!Tom1";
        passCorrecta2 = "J@vaC0deG##ks";
        passCorrecta3 = "t#m1abcD#!";

        peorPass1 = "password";    // ==> Peor pass del archivo
        validadorContrasenia = new ValidadorContrasenia();
    }

    @Test
    public void testPassIncorrecta1() {
        Boolean res = validadorContrasenia.validadContrasenia(passIncorrecta1);
        Assertions.assertEquals(false, res);
    }

    @Test
    public void testPassIncorrecta2() {
        Boolean res = validadorContrasenia.validadContrasenia(passIncorrecta2);
        Assertions.assertEquals(false, res);
    }

    @Test
    public void testPassIncorrecta3() {
        Boolean res = validadorContrasenia.validadContrasenia(passIncorrecta3);
        Assertions.assertEquals(false, res);
    }

    @Test
    public void testPassIncorrecta4() {
        Boolean res = validadorContrasenia.validadContrasenia(passIncorrecta4);
        Assertions.assertEquals(false, res);
    }

    @Test
    public void testPassIncorrecta5() {
        Boolean res = validadorContrasenia.validadContrasenia(passIncorrecta5);
        Assertions.assertEquals(false, res);
    }

    @Test
    public void testPassCorrecta1() {
        Boolean res = validadorContrasenia.validadContrasenia(passCorrecta1);
        Assertions.assertEquals(true, res);
    }

    @Test
    public void testPassCorrecta2() {
        Boolean res = validadorContrasenia.validadContrasenia(passCorrecta2);
        Assertions.assertEquals(true, res);
    }

    @Test
    public void testPassCorrecta3() {
        Boolean res = validadorContrasenia.validadContrasenia(passCorrecta3);
        Assertions.assertEquals(true, res);
    }

    @Test
    public void testPeorContrasena1() {
        Boolean res = validadorContrasenia.validadContrasenia(peorPass1);
        Assertions.assertEquals(false, res);

    }

    @Test
    public void lecturaEscrituraTxt() {

        FileReader entrada = null;
        try {
            String linea = "";
            entrada = new FileReader("E:\\Google Drive\\Facu\\Diseño de Sistemas\\TP Anual\\2022-mi-no-mino-grupo-05\\src\\main\\resources\\peoresContrasenias.txt");
            BufferedReader br = new BufferedReader(entrada);
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            br.close();
        }catch (Exception e){
            System.out.println("Fallo");
        }
    }
}

