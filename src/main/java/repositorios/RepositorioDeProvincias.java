package models;

import domain.db.EntityManagerHelper;
import domain.entidades.Provincia;

import java.util.List;

public class RepositorioDeProvincias {

    public List<Provincia> buscarTodos(){
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Provincia.class.getName())
                .getResultList();
    }

    public Provincia buscar(int id){
        return EntityManagerHelper.getEntityManager().find(Provincia.class, id);
    }

    public void agregar(Provincia provincia){
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().persist(provincia);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

    public void modificar(Provincia provincia){
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().merge(provincia);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

    public void eliminar(Provincia provincia){
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().remove(provincia);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

}
