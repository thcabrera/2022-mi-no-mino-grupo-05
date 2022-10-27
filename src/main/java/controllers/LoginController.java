package controllers;

import domain.db.EntityManagerHelper;
import domain.usuarios.Rol;
import domain.usuarios.Usuario;
import helpers.HashHelper;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController {

    public ModelAndView pantallaLogin(Request request, Response response){
        return new ModelAndView(null, "/login/login.hbs");
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
                response.redirect("/login/2");
            }
        }
        catch (Exception exception) {
            response.redirect("/login/2");
        }
        return response;
    }

    public ModelAndView loginIncorrecto(Request request, Response response){
        return new ModelAndView(null, "/login/login_incorrecto.hbs");
    }

}
