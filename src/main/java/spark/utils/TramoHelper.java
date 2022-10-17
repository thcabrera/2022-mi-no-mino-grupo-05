package spark.utils;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import domain.viaje.Tramo;

import java.io.IOException;

public enum TramoHelper implements Helper<Tramo>{
	
	obtenerInicio{
		@Override
		public CharSequence apply(Tramo tramo, Options arg1) throws IOException {
			return tramo.obtenerInicio();
		}
	},

	obtenerFin{
		@Override
		public CharSequence apply(Tramo tramo, Options arg1) throws IOException {
			return tramo.obtenerFin();
		}
	},

	obtenerTipo{
		@Override
		public CharSequence apply(Tramo tramo, Options arg1) throws IOException {
			return tramo.obtenerTipo();
		}
	}

}
