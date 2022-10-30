package repositorios;

import domain.db.EntityManagerHelper;
import domain.viaje.publico.Linea;
import domain.viaje.publico.Parada;

import java.util.List;

public class RepositorioParadas {
    public List paradasPorLinea(int idLinea) {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from "
                        + Parada.class.getName()
                        + " where linea_id="
                        + idLinea)
                .getResultList();
    }

    public List paradasPorLineaParada(int idLinea, int id_paradaPartida) {
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from "
                        + Parada.class.getName()
                        + " where linea_id="
                        + idLinea
                        + " AND id <>"
                        + id_paradaPartida)
                .getResultList();
    }
}
/*
return EntityManagerHelper
               .getEntityManager()
               .createQuery("from "
                       + Linea.class.getName()
                       + " where tipoLinea="
                       + idTipo)
               .getResultList();
* */