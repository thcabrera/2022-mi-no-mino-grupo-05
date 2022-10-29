package helpers;

import domain.db.EntityManagerHelper;
import domain.entidades.Organizacion;
import spark.Request;

public class OrganizacionHelper {
    public static Organizacion organizacionLogueada(Request request) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Organizacion.class, request.session().attribute("id"));
    }
}
