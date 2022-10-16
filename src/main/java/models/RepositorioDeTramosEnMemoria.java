package models;

import domain.Direccion;
import domain.entidades.Localidad;
import domain.viaje.Tramo;
import domain.viaje.privado.limpio.TramoLimpio;
import domain.viaje.privado.particular.TipoParticular;
import domain.viaje.privado.particular.TramoParticular;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDeTramosEnMemoria {

    private List<Tramo> tramos;

    public RepositorioDeTramosEnMemoria(){
        this.tramos = obtenerTramosDeTesteo();
    }

    private List<Tramo> obtenerTramosDeTesteo(){
        List<Tramo> tramos = new ArrayList<>();
        Direccion origen = new Direccion("Baigorria",3057, new Localidad(1, null));
        Direccion destino = new Direccion("Campana",3500, new Localidad(1, null));
        Tramo tramo = new TramoLimpio("BICI", origen, destino);
        tramo.setId(1);
        Tramo tramo2 = new TramoLimpio("A PATA", destino, origen);
        tramo2.setId(2);
        tramos.add(tramo);
        tramos.add(tramo2);
        TipoParticular autito = new TipoParticular(0.0);
        autito.setDescripcion("Autito");
        Tramo tramo3 = new TramoParticular(null, autito, origen,destino, false);
        tramo3.setId(3);
        tramos.add(tramo3);
        return tramos;
    }

    public Tramo buscar(int id){
        for (Tramo tramo: this.tramos){
            if (tramo.getId() == id){
                return tramo;
            }
        }
        return null;
    }

    public void eliminar(Tramo tramo){
        this.tramos.remove(tramo);
    }

    public void guardar(Tramo tramo){
        this.tramos.add(tramo);
    }

    public List<Tramo> buscarTodos(){
        return this.tramos;
    }

}
