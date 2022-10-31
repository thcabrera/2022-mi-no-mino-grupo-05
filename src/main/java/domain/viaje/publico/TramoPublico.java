package domain.viaje.publico;

import domain.entidades.Persona;
import domain.viaje.Tramo;
import domain.viaje.publico.sentido.SentidoRecorrido;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="tramo_publico")
public class TramoPublico extends Tramo {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parada_inicio_id", referencedColumnName = "id")
    private Parada paradaInicio;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parada_fin_id", referencedColumnName = "id")
    private Parada paradaFin;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "linea_id", referencedColumnName = "id")
    private Linea linea;

    // TODO sacar y calcular con el indice de las paradas:
    // TODO indice parada inicio > indice parada fin --> vuelta
    // TODO sino --> ida
    @Transient
    private SentidoRecorrido sentido;

    //  ----------  GETTERS & SETTERS  ----------
    public TramoPublico(Parada paradaInicio, Parada paradaFin, Linea linea) {
        this.paradaInicio = paradaInicio;
        this.paradaFin = paradaFin;
        this.linea = linea;
    }

    public TramoPublico() {

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
    @Override
    public Double calculoHC(Persona persona){
        return this.consumoPorKM() * this.calcularDistanciaTramo();
    }

    @Override
    public String obtenerInicio() {
        return this.paradaInicio.getNombre();
    }

    @Override
    public String obtenerFin() {
        return this.paradaFin.getNombre();
    }

    @Override
    public String obtenerTipo() {
        return this.linea.getNombreLinea();
    }
}



