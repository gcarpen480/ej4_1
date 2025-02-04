package ies.castillodeluna.ad.backend;

import java.nio.file.Path;
import java.util.Map;

/**
 * Clase que contiene la configuración de la conexión a la base de datos
 */
public class CrearConexion {
    
    /**
     * Metodo para crear la URL de conexion para la base de datos
     * @return Un mapa con los parametros de configuracion de la conexion a la base de datos
     */
    public static Map<String , Object> opciones(){

        Path ruta = Path.of(System.getProperty("user.dir"), "src", "main", "resources", "pedidos.db");
        String protocolo = "jdbc:sqlite:";
        String url = protocolo + ruta.toString();

        return Map.of("base","sqlite", "url", url,"user","","password","");

    }

}
