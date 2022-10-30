package repositorios;

import domain.db.EntityManagerHelper;
import domain.mediciones.consumos.Consumo;
import domain.mediciones.consumos.Periodicidad;

import java.util.List;

public class RepositorioDePeriocidad {
    public List<Periodicidad> buscarTodos(){
        return EntityManagerHelper.getEntityManager()
                .createQuery("from " + Periodicidad.class.getName())
                .getResultList();
    }

    public List<Periodicidad> buscarTodasLasActividades(int idOrg){
        return EntityManagerHelper.getEntityManager()
                .createQuery("from " + Periodicidad.class.getName() + " where org_id = " + idOrg)
                .getResultList();
    }

    public Periodicidad buscar(int id) {
        return EntityManagerHelper.getEntityManager().find(Periodicidad.class, id);
    }

    public void guardar(Periodicidad periodicidad ) {
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().persist(periodicidad);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

    public void eliminar(Periodicidad periodicidad) {
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().remove(periodicidad);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }
}
