package helpers;

import domain.usuarios.Rol;
import domain.usuarios.Usuario;
import spark.Request;

public class PermisoHelper {

    public static Boolean usuarioTieneRol(Request request, Rol rol) {
        Usuario usuario = UsuarioHelper.usuarioLogueado(request);
        return usuario.getRol() == rol;
    }
}
