package models;

import domain.db.EntityManagerHelper;
import domain.entidades.Organizacion;

import java.util.List;

public class RepositorioGenerico {

    public List<Object> buscarTodos(){
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Object.class.getName())
                .getResultList();
    }

}
