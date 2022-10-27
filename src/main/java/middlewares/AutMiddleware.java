package middlewares;

import domain.usuarios.Rol;
import domain.usuarios.Usuario;
import helpers.UsuarioHelper;
import spark.Request;
import spark.Response;
import spark.Spark;

// AuthMiddleware --> middleware de autenticación
// AutMiddleware --> middleware de autorización
public class AutMiddleware {

    public static boolean usuarioTieneRol(Usuario usuario, Rol rol){
        return usuario.getRol() == rol;
    }

    public static Response verificarRol(Request request, Response response, Rol rol){
        if (!usuarioTieneRol(UsuarioHelper.usuarioLogueado(request), rol)){
            response.redirect("/403");
            Spark.halt();
        }
        return response;
    }

    public static Response verificarPersona(Request request, Response response){
        return verificarRol(request, response, Rol.PERSONA);
    }

    public static Response verificarOrganizacion(Request request, Response response){
        return verificarRol(request, response, Rol.ORGANIZACION);
    }

    public static Response verificarAdministrador(Request request, Response response){
        return verificarRol(request, response, Rol.ADMINISTRADOR);
    }

    public static Response verificarAgenteSectorial(Request request, Response response){
        return verificarRol(request, response, Rol.AGENTE_SECTORIAL);
    }

}
