package repositorios;

import domain.db.EntityManagerHelper;
import domain.viaje.publico.TipoLinea;

import java.util.List;

public class RepositorioTipoTransporte {

    public List<TipoLinea> todos(){
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + TipoLinea.class.getName())
                .getResultList();
    }

}
