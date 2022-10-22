package domain.serviciosExternos;

import domain.mediciones.services.calculoDistancia.ServicioDistancia;
import domain.mediciones.services.calculoDistancia.adapters.DistanciaServiceAdapter;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

public class CalculadorDistanciaServiceTest {
    public class DistanciaServiceTest {
        private ServicioDistancia servicioDistancia;
        private DistanciaServiceAdapter adapterMock;

        @BeforeEach
        public void init() {
            this.adapterMock = mock(DistanciaServiceAdapter.class);
            this.servicioDistancia = ServicioDistancia.getInstancia();
            this.servicioDistancia.setAdapter(this.adapterMock);
        }

       /* @Test
        public void setServicioDistanciaTest() throws IOException {
            String distancia = this.distanciaMock();



            Direccion origen = new Direccion("maipu", 100,"", "",   1 );
            Direccion destino = new Direccion("O'Higgins", 200,"", "", 457);

            when()

            Assertions.assertEquals("42.349 KM", this.servicioDistancia.calculoDistancia(origen, destino));

        }*/

        // Dato de prueba
        private String distanciaMock(){
            return "42.349 KM";
        }
    }
}
