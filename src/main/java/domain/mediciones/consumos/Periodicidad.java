package domain.mediciones.consumos;

public abstract class Periodicidad { // Seria interfaz :D

    public boolean coincide(Periodicidad periodicidad){
        return true;
    }

    public Integer getAnio(){
        return null;
    }

//    @Override
//    public String toString() {
//        return String.format("%s %s",
//                this.getClass().getSimpleName());
//    }
}
   /*

    Hola @Federico Prandi tenemos una duda respecto a la implementación de unas funciones en Java.
    Nosotros tenemos la clase abstracta Periodicidad y las subclases Anual y Mensual.
    Nuestra objetivo es poder comparar dos periodos y saber si coinciden...

    En caso de que el primero fuera anual:
    - Si el otro periodo es anual, que coincida el año
    - Si el otro periodo es mensual, que de "MM/YYYY" coincida el "YYYY"

    En caso de que el primero fuera mensual:
    - Si el otro periodo es mensual, tiene que coincidir mes y año
    - Si el otro periodo es anual, y coincide el año, dividimos esa actividad por 12 meses y le asignamos uno

    Nuestro problema es en este segundo caso caso: quisieramos tener una funcion polimorfica coincide(periodo)
    a la cual se le pase o un anual o un mensual, y en cada caso haga la comparacion correspondiente.
    Quisimos hacer dentro de Anual dos funciones con mismo nombre "coincide" pero que una reciba como parametro
    un Anual y la otra un Mensual, de esta manera entra a la funcion que corresponde segun el parametro.
    El tema es que la clase padre abstracta Periodicidad tiene el metodo coincide(Periodicidad), entonces no se si
    me va a tomar los otros dos metodos que reciben a las clases hijas.
    */