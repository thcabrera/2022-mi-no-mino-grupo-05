package models;

import domain.db.EntityManagerHelper;
import domain.entidades.Localidad;

import java.util.List;

public class RepositorioDeLocalidades {

    public List<Localidad> buscarTodos(){
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Localidad.class.getName())
                .getResultList();
    }

    public Localidad buscar(int id){
        return EntityManagerHelper.getEntityManager().find(Localidad.class, id);
    }

    public void agregar(Localidad localidad){
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().persist(localidad);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

    public void modificar(Localidad localidad){
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().merge(localidad);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

    public void eliminar(Localidad localidad){
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().remove(localidad);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

}
