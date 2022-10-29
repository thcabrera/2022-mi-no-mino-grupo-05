package repositorios;

import domain.db.EntityManagerHelper;
import domain.entidades.Municipio;

import java.util.List;

public class RepositorioDeMunicipios {

    public List<Municipio> buscarTodos(){
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Municipio.class.getName())
                .getResultList();
    }

    public Municipio buscar(int id){
        return EntityManagerHelper.getEntityManager().find(Municipio.class, id);
    }

    public void agregar(Municipio municipio){
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().persist(municipio);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

    public void modificar(Municipio municipio){
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().merge(municipio);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

    public void eliminar(Municipio municipio){
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().remove(municipio);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

}
