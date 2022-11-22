package repositorios;

import domain.db.EntityManagerHelper;
import domain.entidades.Organizacion;
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

    public void eliminar(Actividad actividad) {
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().remove(actividad);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

    private String queryAnual(Integer anio){
        return "from " + Actividad.class.getSimpleName()
                + " as a where a.anio = " + anio;
    }

    public String queryMensual(Integer anio, Integer mes){
        return "from " + Actividad.class.getSimpleName()
                + " as a where a.anio = " + anio
                + " and (a.mes = " + mes
                + " or a.mes = NULL)";
    }

    public List<Actividad> buscarActividadesPorPeriodo(Integer anio, Integer mes){
        String query = "";
        if (mes == null) {
            query = queryAnual(anio);
        } else{
            query = queryMensual(anio, mes);
        }
        return EntityManagerHelper.getEntityManager()
                .createQuery(query)
                .getResultList();
    }

    public List<Actividad> buscarActividadesPorPeriodo(Integer anio, Integer mes, Organizacion organizacion){
        String query = "";
        if (mes != null) {
            query = queryAnual(anio);
        } else{
            query = queryMensual(anio, mes);
        }
        query += " and a.organizacion = " + organizacion.getId();
        return EntityManagerHelper.getEntityManager()
                .createQuery(query)
                .getResultList();
    }

}
