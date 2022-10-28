package controllers;

import domain.db.EntityManagerHelper;
import domain.usuarios.Rol;
import domain.usuarios.Usuario;
import helpers.HashHelper;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class LoginController {

    public ModelAndView pantallaLogin(Request request, Response response){
        String estado = request.queryParams("estado");
        Map<String, Object> parametros = new HashMap<>();
        if (estado != null)
            parametros.put("contraseñaIncorrecta", true);
        return new ModelAndView(parametros, "/login/login.hbs");
    }

    public Response login(Request request, Response response){
        try {
            String query = "from "
                    + Usuario.class.getName()
                    +" WHERE nombreDeUsuario='"
                    + request.queryParams("nombre_de_usuario")
                    +"' AND contrasenia='"
                    + HashHelper.hashear(request.queryParams("contrasenia"))
                    +"'";

            Usuario usuario = (Usuario) EntityManagerHelper
                    .getEntityManager()
                    .createQuery(query)
                    .getSingleResult();

            if(usuario != null) {
                request.session(true);
                request.session().attribute("id", usuario.getId());
                if (usuario.getRol() == Rol.PERSONA)
                    response.redirect("/user/principal");
                else if (usuario.getRol() == Rol.ORGANIZACION)
                    response.redirect("/organizacion/principal");
                else if (usuario.getRol() == Rol.AGENTE_SECTORIAL)
                    response.redirect("/agente_sectorial/principal");
                else if (usuario.getRol() == Rol.ADMINISTRADOR)
                    response.redirect("/administrador/principal");
                else response.redirect("/404");
            }
            else {
                response.redirect("/login?estado=incorrecto");
            }
        }
        catch (Exception exception) {
            response.redirect("/login?estado=incorrecto");
        }
        return response;
    }

    public Response logout(Request request, Response response){
        request.session().invalidate();
        response.redirect("/login");
        return response;
    }

}
