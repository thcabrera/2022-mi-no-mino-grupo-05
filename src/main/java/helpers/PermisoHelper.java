package helpers;


import domain.usuarios.Permiso;
import spark.Request;

public class PermisoHelper {

    public static Boolean usuarioTienePermisos(Request request, Permiso... permisos) {
        return UsuarioHelper
                .usuarioLogueado(request)
                .getRol()
                .tenesTodosLosPermisos(permisos);
    }
}
