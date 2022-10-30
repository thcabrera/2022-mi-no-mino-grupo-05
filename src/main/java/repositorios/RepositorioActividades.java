package repositorios;

import domain.db.EntityManagerHelper;
import domain.mediciones.consumos.actividades.Actividad;
import domain.mediciones.consumos.actividades.ActividadConsumo;

import java.util.List;

public class RepositorioActividades {

    public List<ActividadConsumo> buscarTodos(){
        return EntityManagerHelper.getEntityManager()
                .createQuery("from " + ActividadConsumo.class.getName())
                .getResultList();
    }

    public List<ActividadConsumo> buscarTodasLasActividades(int idOrg){
        return EntityManagerHelper.getEntityManager()
                .createQuery("from " + ActividadConsumo.class.getName() + " where org_id = " + idOrg)
                .getResultList();
    }

    public ActividadConsumo buscar(int id) {
        return EntityManagerHelper.getEntityManager().find(ActividadConsumo.class, id);
    }

    public void guardar(Actividad actividad) {
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().persist(actividad);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

    public void eliminar(ActividadConsumo actividad) {
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().remove(actividad);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

}
