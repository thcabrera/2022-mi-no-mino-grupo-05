package controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import domain.db.EntityManagerHelper;
import domain.entidades.Documentacion;
import domain.usuarios.Rol;
import domain.usuarios.Usuario;
import helpers.HashHelper;
import org.dom4j.rule.Mode;
import seguridad.ValidadorContrasenia;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LoginController {
    private ValidadorContrasenia validadorContrasenia = new ValidadorContrasenia();

    public ModelAndView pantallaLogin(Request request, Response response){
        System.out.println("status de la respoonse" + response.status() + "body" + response.body() + " algo amas ");
        String estado = request.queryParams("estado");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("loginBase", true);

        if (response.body() == "invalido"){
            parametros.put("contraseñaIncorrecta", true);
        }
        return new ModelAndView(parametros, "/login/login.hbs");
    }


    public ModelAndView login(Request request, Response response){
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
                return mostrarLoginIncorrecto();
            }
        }
        catch (Exception exception) {
            return mostrarLoginIncorrecto();
        }
        return null;
    }

    public Response logout(Request request, Response response){
        request.session().invalidate();
        response.redirect("/login");
        return response;
    }

    public ModelAndView mostrarLoginIncorrecto(){
        Map<String, Object> parametros = new HashMap<>();

        parametros.put("contraseñaIncorrecta", true);
        parametros.put("loginBase", true);
        return new ModelAndView(parametros, "/login/login.hbs");
    }


    public ModelAndView pantallacrearUser(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();

        parametros.put("tipoDoc", this.getDocumentacionValues());
        parametros.put("loginBase", false);
        return new ModelAndView(parametros, "login/create_user.hbs");
    }

    public ModelAndView crearUsuario(Request request, Response response) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("tipoDoc", this.getDocumentacionValues());
        String contra =  request.queryParams("contrasenia");

        if( validadorContrasenia.validarContrasenia(contra) ) {
            //todo:
        }else {

            parametros.put("contraseniaDebil", true);
            parametros.put("loginBase", false);
            return new ModelAndView(parametros, "login/create_user.hbs");

        }
        //todo: persisitir el user bla bla
        response.redirect("user/principal");

        return null;
    }
    private List<Documentacion> getDocumentacionValues(){
        return  Arrays
                .stream(Documentacion.values())
                .collect(Collectors.toList());
    }
}
