package models;

import domain.db.EntityManagerHelper;
import domain.entidades.*;
import java.util.List;

public class RepositorioDeAreas {

    public List<Area> buscarTodos(){
        return EntityManagerHelper.getEntityManager()
                .createQuery("from " + Area.class.getName())
                .getResultList();
    }

    public Area buscar(int id) {
        return EntityManagerHelper.getEntityManager().find(Area.class, id);
    }

    public void guardar(Area area) {
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().persist(area);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

    public void eliminar(Area area) {
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().remove(area);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }



}
