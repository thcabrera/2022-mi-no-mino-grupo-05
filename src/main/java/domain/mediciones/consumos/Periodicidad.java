package domain.mediciones.consumos;

import java.util.Objects;

public class Periodicidad {

    public static boolean coincide(Integer anioBuscado, Integer mesBuscado, Integer anio, Integer mes){
        if (esMensual(anioBuscado, mesBuscado) && esMensual(anio, mes)) {
            return Objects.equals(anioBuscado, anio) && Objects.equals(mesBuscado, mes);
        }
        return Objects.equals(anioBuscado, anio);
    }

    public static Double obtenerPorcentaje(Integer anioBuscado, Integer mesBuscado,
                                           Integer anio, Integer mes){
        if (esAnual(anioBuscado, mesBuscado) && esAnual(anio, mes)){
            if (!coincide(anioBuscado, mesBuscado, anio, mes))
                return 0.0;
            return 1.0;
        }
        if (esMensual(anioBuscado, mesBuscado) && esMensual(anio, mes)){
            if (!coincide(anioBuscado, mesBuscado, anio, mes))
                return 0.0;
            return 1.0;
        }
        if (esMensual(anioBuscado, mesBuscado) && esAnual(anio, mes)){
            if (!coincide(anioBuscado, mesBuscado, anio, mes))
                return 0.0;
            return 1.0/12.0;
        }
//        la unica opcion que queda es:
//        esAnual(anioBuscado, mesBuscado) && esMensual(anio, mes)
        if (!coincide(anioBuscado, mesBuscado, anio, mes))
            return 0.0;
        return 1.0;
    }

    public static boolean esMensual(Integer anio, Integer mes){
        return mes != null;
    }

    public static boolean esAnual(Integer anio, Integer mes){
        return !esMensual(anio, mes);
    }


}