package models;

import domain.db.EntityManagerHelper;
import domain.mediciones.consumos.Anual;
import domain.mediciones.consumos.Mensual;
import domain.mediciones.consumos.Periodicidad;

import java.util.List;

public class RepositorioDePeriodicidad {
    /*
    public List<Periodicidad> buscarTodos(){
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Periodicidad.class.getName())
                .getResultList();
    }

     */
/*-- ------------- ANUAL -_-------------- */
    public List<Anual> buscarTodosAnuales(){
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Anual.class.getName())
                .getResultList();
    }

    public Anual buscarAnual(int id){
        return EntityManagerHelper.getEntityManager().find(Anual.class, id);
    }
/*
    public Periodicidad buscar(int id){
        return EntityManagerHelper.getEntityManager().find(Periodicidad.class, id);
    }

 */

    public void agregar(Periodicidad periodicidad){
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().persist(periodicidad);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

    public void modificar(Periodicidad periodicidad){
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().merge(periodicidad);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

    public void eliminar(Periodicidad periodicidad){
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().remove(periodicidad);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

    /*----------------- Mensual ---------------- */
    public List<Mensual> buscarTodosMensuales(){
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Mensual.class.getName())
                .getResultList();
    }

    public Anual buscarMensual(int id){
        return EntityManagerHelper.getEntityManager().find(Anual.class, id);
    }

}
