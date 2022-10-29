package repositorios;

import domain.db.EntityManagerHelper;

import java.util.List;

public class RepositorioGenerico {

    public List<Object> buscarTodos(){
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Object.class.getName())
                .getResultList();
    }

    public Object buscar(int id){
        return null;
    }

    public void eliminar(Object o){

    }

    public void guardar(Object o){

    }

}
