package repositorios;

import domain.db.EntityManagerHelper;
import domain.viaje.publico.Linea;
import domain.viaje.publico.Parada;

import java.util.List;

public class RepositorioLineas {

    public List lineasPorTipo(int idTipo){

        return EntityManagerHelper
               .getEntityManager()
               .createQuery("from "
                       + Linea.class.getName()
                       + " where tipoLinea="
                       + idTipo)
               .getResultList();
    }

    public Linea buscar(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Linea.class, id);
    }

}
