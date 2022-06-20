package domain.mediciones.importador;

import domain.mediciones.consumos.actividades.Actividad;

import java.util.ArrayList;

public class Importador {
    private ImportarExcel estrategiaImportacion;
    private Documento importable;

    public ArrayList<Actividad> importar(String path){
        return null;
    }

    public void setImportable(Documento importable){
        this.importable = importable;
    }


}
