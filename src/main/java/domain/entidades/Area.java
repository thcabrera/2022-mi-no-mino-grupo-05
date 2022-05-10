package domain.entidades;

import java.util.List;

public class Area {
    private Organizacion organizacion;
    private List<Persona> miembros;

    public void agregarMiembro(Persona persona){
        miembros.add(persona);
    }
}
