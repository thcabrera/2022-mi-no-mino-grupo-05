package models;

import com.sun.xml.internal.stream.Entity;
import domain.db.EntityManagerHelper;
import domain.entidades.Provincia;
import domain.viaje.publico.Linea;
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
