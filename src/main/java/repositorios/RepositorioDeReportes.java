package repositorios;

import domain.db.EntityManagerHelper;
import domain.entidades.HuellaDeCarbono;
import domain.entidades.Organizacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDeReportes {
    private List<HuellaDeCarbono> huellasDeCarbono = new ArrayList<>();

    public List<HuellaDeCarbono> buscarTodasLasHuellas(){
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + HuellaDeCarbono.class.getName())
                .getResultList();
    }



    public Object buscar(int id){
        return null;
    }

    public void eliminar(Object o){

    }

    public void guardar(Object o){

    }
    public RepositorioDeReportes(){

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

        huellasDeCarbono.add(h1);
        huellasDeCarbono.add(h2);
        huellasDeCarbono.add(h3);
        huellasDeCarbono.add(h4);

        Organizacion organizacion = new Organizacion("1", null, null, null);
        organizacion.setHuellasDeCarbono(huellasDeCarbono);
    }



    public List<HuellaDeCarbono> buscarTodosEjemplo(){

        return this.huellasDeCarbono;
    }


}

