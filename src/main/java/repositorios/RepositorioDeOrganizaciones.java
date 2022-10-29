package models;

import domain.db.EntityManagerHelper;
import domain.entidades.Organizacion;
import domain.entidades.Persona;

import java.util.List;

public class RepositorioDeOrganizaciones {

    public List<Organizacion> buscarTodos(){
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Organizacion.class.getName())
                .getResultList();
    }

    public Organizacion buscar(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Organizacion.class, id);
    }

    public void guardar(Organizacion organizacion) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper
                .getEntityManager()
                .persist(organizacion);
        EntityManagerHelper.commit();
    }

    public void eliminar(Organizacion organizacion){
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper
                .getEntityManager()
                .remove(organizacion);
        EntityManagerHelper.commit();
    }

}
