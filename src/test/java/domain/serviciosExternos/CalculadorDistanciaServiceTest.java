package test;

import domain.services.calculoDistancia.ServicioDistancia;
import domain.services.calculoDistancia.adapters.DistanciaServiceAdapter;
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
/*
        @Test
        public void setServicioDistanciaTest{
            //ServicioDistancia


        }*/
    }
}
