package repositorios;

import domain.db.EntityManagerHelper;
import domain.viaje.privado.contratado.Servicio;
import domain.viaje.privado.particular.Combustible;
import domain.viaje.privado.particular.TipoParticular;
import domain.viaje.publico.TipoLinea;

import java.util.List;

public class RepositorioTipoTransporte {

    public List<TipoLinea> todos(){
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + TipoLinea.class.getName())
                .getResultList();
    }

    public List<Servicio> serviciosContratados(){
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Servicio.class.getName())
                .getResultList();
    }

    public List<Combustible> combustibles(){
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Combustible.class.getName())
                .getResultList();
    }

    public List<TipoParticular> tiposDeVehiculos(){
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + TipoParticular.class.getName())
                .getResultList();
    }
}
