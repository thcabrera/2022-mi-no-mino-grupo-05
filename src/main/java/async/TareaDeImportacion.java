package async;

import domain.db.EntityManagerHelper;
import domain.entidades.Organizacion;
import domain.mediciones.consumos.MedioTransporte;
import domain.mediciones.consumos.actividades.Actividad;
import domain.mediciones.consumos.actividades.TipoActividadConsumo;
import domain.mediciones.consumos.tipoConsumo.TipoConsumo;
import domain.mediciones.importador.ImportarExcel;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TareaDeImportacion implements Runnable {

    private final Organizacion organizacion;
    private final String path;

    public TareaDeImportacion(String path, Organizacion organizacion){
        this.path = path;
        this.organizacion = organizacion;
    }

    private Map<String, TipoConsumo> filtrarPorTipoActividad(List<TipoConsumo> tipoConsumos,
                                                            TipoActividadConsumo tipoActividadConsumo){
        return tipoConsumos
                .stream()
                .filter(c -> c.getTipoActividad().equals(tipoActividadConsumo))
                .collect(Collectors.toMap(TipoConsumo::getDescripcion, item -> item));
    }

    private List<TipoConsumo> obtenerTiposDeConsumo(){
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + TipoConsumo.class.getName())
                .getResultList();
    }

    private List<MedioTransporte> obtenerMediosDeTransporte(){
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + MedioTransporte.class.getName())
                .getResultList();
    }

    private void guardarMediciones(List<Actividad> mediciones){
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        mediciones.forEach(m -> EntityManagerHelper.getEntityManager().persist(m));
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

    private void eliminarArchivoCargado() throws IOException {
        File archivo = new File(path);
        if(archivo.delete()){
            System.out.printf("Archivo %s eliminado.\n", path);
            return;
        }
        throw new IOException("No se pudo eliminar el archivo temporal " + path);
    }

    @Override
    public void run() {
        List<TipoConsumo> tiposConsumos = obtenerTiposDeConsumo();
        List<MedioTransporte> medioTransportes = obtenerMediosDeTransporte();

        Map<String, MedioTransporte> medioTransporteMap = medioTransportes
                .stream().collect(Collectors.toMap(MedioTransporte::getDescripcion, m -> m));

        Map<String, TipoConsumo> tiposConsumosFijos = filtrarPorTipoActividad(tiposConsumos,
                TipoActividadConsumo.COMBUSTION_FIJA);

        Map<String, TipoConsumo> tiposConsumosMoviles = filtrarPorTipoActividad(tiposConsumos,
                TipoActividadConsumo.COMBUSTION_MOVIL);

        Map<String, TipoConsumo> tiposConsumosElectricidad = filtrarPorTipoActividad(tiposConsumos,
                TipoActividadConsumo.ELECTRICIDAD);

        ImportarExcel importador = new ImportarExcel(
                tiposConsumosFijos,
                tiposConsumosMoviles,
                tiposConsumosElectricidad,
                medioTransporteMap);
        List<Actividad> mediciones = importador.importar(path);
        if (mediciones != null) {
            // agregamos las mediciones a la org
            organizacion.agregarMediciones(mediciones);
            // persistimos las actividades
            guardarMediciones(mediciones);
        }

        try{
            eliminarArchivoCargado();
        } catch(IOException e){
            e.printStackTrace();
        }

    }

}
