package domain.entidades;

import domain.mediciones.consumos.Periodicidad;

// de un sector solo nos interesa calcular su huella de carbono, absolutamente nada mas
public abstract class Sector {

    public abstract Double calculoHC(Periodicidad periodo);

}
