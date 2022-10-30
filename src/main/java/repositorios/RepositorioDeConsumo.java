package repositorios;

import domain.db.EntityManagerHelper;
import domain.mediciones.consumos.Consumo;
import domain.mediciones.consumos.actividades.Actividad;

import java.util.List;

public class RepositorioDeConsumo {
    public List<Consumo> buscarTodos(){
        return EntityManagerHelper.getEntityManager()
                .createQuery("from " + Consumo.class.getName())
                .getResultList();
    }

    public List<Consumo> buscarTodasLasActividades(int idOrg){
        return EntityManagerHelper.getEntityManager()
                .createQuery("from " + Consumo.class.getName() + " where org_id = " + idOrg)
                .getResultList();
    }

    public Consumo buscar(int id) {
        return EntityManagerHelper.getEntityManager().find(Consumo.class, id);
    }

    public void guardar(Consumo consumo ) {
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().persist(consumo);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

    public void eliminar(Consumo consumo) {
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().remove(consumo);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }
}
