package helpers;

import domain.db.EntityManagerHelper;
import domain.usuarios.Usuario;

import spark.Request;

public class UsuarioHelper {

    public static Usuario usuarioLogueado(Request request) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Usuario.class, request.session().attribute("id"));
    }
}
