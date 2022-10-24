package controllers;

import domain.db.EntityManagerHelper;
import domain.entidades.AgenteSectorial;
import domain.entidades.Organizacion;
import domain.entidades.Persona;
import domain.usuarios.Usuario;
import helpers.HashHelper;
import org.etsi.uri.x01903.v13.ResponderIDType;
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
                if (usuario.getActor() instanceof Persona)
                    response.redirect("/user/principal");
                else if (usuario.getActor() instanceof Organizacion)
                    response.redirect("/organizacion/principal");
                else if (usuario.getActor() instanceof AgenteSectorial)
                    response.redirect("/agente_sectorial/principal");
                else response.redirect("/administrador/principal");
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
