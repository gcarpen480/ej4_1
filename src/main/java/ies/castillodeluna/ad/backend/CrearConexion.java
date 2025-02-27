package ies.castillodeluna.ad.backend;

import java.util.Map;

/**
 * Clase que contiene la configuración de la conexión a la base de datos
 */
public class CrearConexion {
    
    /**
     * Metodo para crear la URL de conexion para la base de datos
     * @return Un mapa con los parametros de configuracion de la conexion a la base de datos
     */
    public static Map<String, Object> opciones() {
        // Usar la misma ruta que en hibernate.cfg.xml
        String protocolo = "jdbc:sqlite:";
        String url = protocolo + "src/main/resources/pedidos.db";

        System.out.println("URL de conexión configurada: " + url);

        return Map.of("base", "hibernate", "url", url, "user", "", "password", "");
    }
}