package models;

import domain.db.EntityManagerHelper;
import domain.viaje.publico.Linea;

import java.util.List;

public class RepositorioLineas {

    public List lineasPorTipo(int idTipo){

        return EntityManagerHelper
               .getEntityManager()
               .createQuery("from "
                       + Linea.class.getName()
                       + " where tipoLinea="
                       + idTipo)
               .getResultList();

    }

}
