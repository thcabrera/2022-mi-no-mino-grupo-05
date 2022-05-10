package domain.viaje.limpio;

import com.sun.xml.internal.bind.v2.TODO;
import domain.Direccion;
import domain.viaje.Trameable;
import org.omg.CORBA.INTERNAL;
import org.omg.CORBA.PRIVATE_MEMBER;

public class Limpio implements Trameable {
    public String tipo;
    private Direccion direccionInicio;
    private Direccion direccionFin;

    @Override
    public Integer consumo(){
        return 0;
    }

    @Override
    public Integer calcularDistanciaTramo() {
        return null;
    }
}
