package domain.viaje.publico;

import domain.entidades.Persona;
import domain.viaje.Trameable;
import domain.viaje.publico.sentido.SentidoRecorrido;

import java.util.List;

public class TPublico implements Trameable {
    private Parada paradaInicio;
    private Parada paradaFin;
    private Linea linea;
    private SentidoRecorrido sentido;

    //  ----------  GETTERS & SETTERS  ----------
    public TPublico(Parada paradaInicio, Parada paradaFin, Linea linea) {
        this.paradaInicio = paradaInicio;
        this.paradaFin = paradaFin;
        this.linea = linea;
    }

    //  ----------  CONSUMO  ----------
    public Double consumoPorKM(){
        return this.linea.getConsumo();
    }

    public boolean getEsCompartido(){
        return false;
    }

    @Override
    public Persona getPropietario() {
        return null;
    }

    //  ----------  CALCULO DISTANCIA  ----------

    public Double calcularDistanciaTramo(){
        List<Parada> paradasIntermedias;
        paradasIntermedias = this.solicitarParadasIntermedias();
        return calcularDistancia(paradasIntermedias);
    }

    public List<Parada> solicitarParadasIntermedias(){
        sentido = this.obtenerSentido();
        return linea.getParadasIntermedias(paradaInicio, paradaFin, sentido);
    }

    private Double calcularDistancia(List<Parada> paradasIntermedias) {
        return paradasIntermedias.stream()
                .mapToDouble(parada -> sentido.getDistanciaProxParada(parada))
                .sum();
    }

    private SentidoRecorrido obtenerSentido(){
        return linea.getSentidoRecorrido(paradaInicio,paradaFin);
    }

    //  ----------  CALCULO HC  ----------

    public Double calculoHC(Persona persona){ // todo falta la suma de cuanto anduvo
        return this.consumoPorKM() * this.calcularDistanciaTramo();
    }
}



