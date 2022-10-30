package repositorios;

import domain.db.EntityManagerHelper;
import domain.viaje.Tramo;
import domain.viaje.privado.contratado.TramoContratado;

import java.util.List;

public class RepositorioDeTramos {

    public List<Tramo> buscarTodos(){
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Tramo.class.getName())
                .getResultList();
    }

    public Tramo buscar(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Tramo.class, id);
    }
    public void guardar(Tramo tramo) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper
                .getEntityManager()
                .persist(tramo);
        EntityManagerHelper.commit();
    }

    public void eliminar(Tramo tramo){
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper
                .getEntityManager()
                .remove(tramo);
        EntityManagerHelper.commit();
    }

    public void modificar(Tramo tramo){
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().merge(tramo);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

}
