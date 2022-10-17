package spark.utils;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

import java.io.IOException;

public enum NumberHelper implements Helper<Integer>{
	
	incrementar{
		@Override
		public CharSequence apply(Integer numero, Options arg1) throws IOException {
			return Integer.toString(numero + 1);
		}
	}
	
}
