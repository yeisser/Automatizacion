package pe.com.tatiendo.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public final class CoreUtil {

	private final static String ARCHIVO = "propiedades";

    private CoreUtil() {
    }

    //Metodo para leer un archivo de properties
    public static final String obtenerPropiedad(String clave) {
        ResourceBundle rs = ResourceBundle.getBundle(ARCHIVO);
        return rs.getString(clave);
    }

    //Dar formato a una fecha
    public static final String convertirDate(Date fecha, String formato) {
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        return sdf.format(fecha);
    }
}
