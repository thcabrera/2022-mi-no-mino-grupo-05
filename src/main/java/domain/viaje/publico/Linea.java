package domain.viaje.publico;

import domain.viaje.publico.sentido.Ida;
import domain.viaje.publico.sentido.SentidoRecorrido;
import domain.viaje.publico.sentido.Vuelta;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter

@Entity
@Table(name="linea")
public class Linea {
    @Id
    @GeneratedValue
    private Integer id;

    // ES ASI???
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "parada_id", referencedColumnName = "id")//FK DE LA TABLA Parada HACIA LA TABLA Linea
    private List<Parada> paradas;


    @Column(name="descripcion")
    private String nombreLinea;

    @ManyToOne
    @JoinColumn(name = "tipo_linea_id", referencedColumnName = "id")
    private TipoLinea tipoLinea;

    public Double getConsumo() {
        return this.tipoLinea.getConsumo();
    }

    public void setConsumo(Double nuevoConsumo) {
        this.tipoLinea.setConsumo(nuevoConsumo);
    }

    public String getNombreLinea() {
        return nombreLinea;
    }

    public List<Parada> getParadasIntermedias(Parada inicio, Parada fin, SentidoRecorrido sentido) {
        int indInicial = this.paradas.indexOf(inicio);
        int indFinal = this.paradas.indexOf(fin);
        return this.paradas.
                    stream().
                    filter(parada -> sentido.isBetween(indInicial, indFinal, paradas.indexOf(parada))).
                    collect(Collectors.toList());
    }

    public SentidoRecorrido getSentidoRecorrido(Parada inicio, Parada fin){
        int indInicial = this.paradas.indexOf(inicio);
        int indFinal = this.paradas.indexOf(fin);
        if (indInicial < indFinal) {
            return new Ida();
        } else {
            return new Vuelta();
        }
    }

    public void agregarParadas(Parada ... newParadas) {
        Collections.addAll(this.paradas, newParadas);
    }

    public List<Parada> getParadas(){
        return paradas;
    }

}
