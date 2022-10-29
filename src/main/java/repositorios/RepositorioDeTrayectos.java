package repositorios;

import domain.db.EntityManagerHelper;
import domain.entidades.Organizacion;
import domain.viaje.Trayecto;

import java.util.List;

public class RepositorioDeTrayectos {

    public List<Trayecto> buscarTodos(){
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Trayecto.class.getName())
                .getResultList();
    }

    public Trayecto buscar(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Trayecto.class, id);
    }

    public void guardar(Trayecto trayecto) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper
                .getEntityManager()
                .persist(trayecto);
        EntityManagerHelper.commit();
    }

    public void eliminar(Trayecto trayecto){
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper
                .getEntityManager()
                .remove(trayecto);
        EntityManagerHelper.commit();
    }

    public void modificar(Trayecto trayecto){
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().merge(trayecto);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

}
