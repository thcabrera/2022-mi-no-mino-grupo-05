package domain.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@Table(name="localidad")
public class Localidad {
    @Id
    private Integer id;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name = "codigo_postal")
    private String codigoPostal;

    @ManyToOne
    @JoinColumn(name = "municipio_id", referencedColumnName = "id")
    private Municipio municipio;

    public Localidad(){

    }

    public Localidad(Integer id, Municipio municipio){
        this.id = id;
        this.municipio = municipio;
    }

    public Localidad(Municipio municipio){
        this.municipio = municipio;
    }
    public Localidad(Municipio municipio, String descripcion){
        this.municipio = municipio;
        this.descripcion = descripcion;
    }

    public LocalidadDTO convertirADTO(){
        return new LocalidadDTO(this);
    }

    public class LocalidadDTO{

        public int id;
        public String descripcion;

        public LocalidadDTO(Localidad localidad){
            this.id = localidad.id;
            this.descripcion = localidad.descripcion;
        }

    }

}
