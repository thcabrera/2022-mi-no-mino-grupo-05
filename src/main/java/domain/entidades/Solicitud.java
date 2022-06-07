package domain.entidades;

public class Solicitud {
    private Persona solicitante;
    private Area areaSolicitada;

    public Solicitud(Persona solicitante, Area areaSolicitada) {
        this.solicitante = solicitante;
        this.areaSolicitada = areaSolicitada;
    }

    public Persona getSolicitante() {
        return solicitante;
    }

    public Area getAreaSolicitada() {
        return areaSolicitada;
    }
}
