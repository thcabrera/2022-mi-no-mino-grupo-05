package models;

import domain.db.EntityManagerHelper;
import domain.entidades.Area;
import domain.entidades.Solicitud;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeSolicitudes {
    RepositorioDeOrganizaciones repositorioDeOrganizaciones = new RepositorioDeOrganizaciones();

    public List<Solicitud> buscarTodosParaOrg(Integer idOrg){
        List<Solicitud> solicitudes = new ArrayList<>();
        try {
             return   repositorioDeOrganizaciones.buscar(idOrg).getSolicitudes();
        }catch (NullPointerException e){
            return null;
        }
    }
/*
    public List<Solicitud> buscarEnOrganizacion(Integer idOrg, Integer idSol){
        return  repositorioDeOrganizaciones.buscar(idOrg).getSolicitudConId(idSol); // todo: hacer q solo traiga esta solicitud en particular
    }


 */
    public List<Solicitud> buscarTodos(){
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Solicitud.class.getName())
                .getResultList();
    }
    public Solicitud buscar(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Solicitud.class, id);
    }

    public void guardar(Solicitud solicitud) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper
                .getEntityManager()
                .persist(solicitud);
        EntityManagerHelper.commit();
    }

    public void eliminar(Solicitud solicitud) {
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().remove(solicitud);
        EntityManagerHelper.getEntityManager().getTransaction().commit();    }
}

