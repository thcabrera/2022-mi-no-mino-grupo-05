package helpers;

import domain.usuarios.Rol;
import spark.Request;

public class RolHelper {

    public static Boolean usuarioTieneRol(Request request, Rol rol) {
        return UsuarioHelper.usuarioLogueado(request).getRol() == rol;
    }
}
