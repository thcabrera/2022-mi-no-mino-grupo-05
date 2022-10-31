package controllers;

import domain.entidades.Organizacion;
import domain.entidades.Persona;
import helpers.UsuarioHelper;
import org.checkerframework.checker.units.qual.A;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CalculadoraHCController {

    public ModelAndView pantallaCalculadoraHC(Request request, Response response){
        // hay que pasar 4 parámetros:
        // organizaciones --> listado de NOMBRES de las organizaciones a las que pertenece la persona
        // valoresHC --> listado de VALORES de HC de la persona en cada org (siguiendo el indice)
        // porcentajesHC --> listado de PORCENTAJES de HC de la persona en cada org (siguiendo el índice)
        // hcTotal --> la suma de todos los valores de HC de la persona (double + "" lo parsea a String)
        Persona persona = (Persona) UsuarioHelper.usuarioLogueado(request).getActor();
        List<Organizacion> organizaciones = persona.obtenerOrganizaciones();
        List<Persona.CalculoHCDTO> calculosHCDTOS = organizaciones
                .stream().map(persona::convertirACalculoHCDTO)
                .collect(Collectors.toList());
        Double hcTotal = calculosHCDTOS.stream().mapToDouble(c -> c.valorHC).sum();
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("calculos", calculosHCDTOS);
        parametros.put("hcTotal", hcTotal);
        return new ModelAndView(parametros, "/user/us_calculadora_hc.hbs");
    }

}
