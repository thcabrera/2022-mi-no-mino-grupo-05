package domain.usuarios;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "rol")
@Setter
@Getter
public class Rol {

    @Id
    @GeneratedValue
    private Integer id;

    @Column (name = "nombre_rol")
    private String nombre;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "rol_permiso", joinColumns = @JoinColumn(name = "rol_id"))
    @Enumerated(EnumType.STRING)
    private Set<Permiso> permisos;

    public Rol() {
        this.permisos = new LinkedHashSet<>();
    }

    public void agregarPermiso(Permiso permiso) {
        this.permisos.add(permiso);
    }

    public Boolean tenesPermiso(Permiso permiso) {
        return this.permisos.contains(permiso);
    }

    public Boolean tenesTodosLosPermisos(Permiso ... permisos) {
        return Arrays.stream(permisos).allMatch(p -> this.permisos.contains(p));
    }
}
