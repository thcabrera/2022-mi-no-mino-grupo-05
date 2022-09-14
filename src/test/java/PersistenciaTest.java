import domain.entidades.HuellaDeCarbono;
import domain.entidades.Organizacion;
import domain.services.creacionReporte.ReportGenerator;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class PersistenciaTest {

    @Test
    public void generarReporteEvolucionDeHcDeOrgTest(){
        HuellaDeCarbono h1 = new HuellaDeCarbono();
        h1.setFechaMedicion(LocalDate.of(2019,4,24));
        h1.setValor(386.17);
        HuellaDeCarbono h2 = new HuellaDeCarbono();
        h2.setFechaMedicion(LocalDate.of(2020,4,24));
        h2.setValor(528.78);
        HuellaDeCarbono h3 = new HuellaDeCarbono();
        h3.setFechaMedicion(LocalDate.of(2021,4,24));
        h3.setValor(708.98);
        HuellaDeCarbono h4 = new HuellaDeCarbono();
        h4.setFechaMedicion(LocalDate.of(2022,4,24));
        h4.setValor(1052.78);

        ArrayList<HuellaDeCarbono> huellas = new ArrayList<>();
        huellas.add(h1);
        huellas.add(h2);
        huellas.add(h3);
        huellas.add(h4);

        Organizacion organizacion = new Organizacion("1", null, null, null);
        organizacion.setHuellasDeCarbono(huellas);
        new ReportGenerator().evolucionDeHCTotal(organizacion);


    }

}
