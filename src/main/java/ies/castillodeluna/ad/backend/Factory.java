package ies.castillodeluna.ad.backend;

import java.util.Map;
import ies.castillodeluna.ad.backend.sqlite.ConexionSqlite;

/**
 * Clase Factory encargada de crear conexiones con SQLite
 */
public class Factory{
    
    /**
     * Tipo de base de datos soportada
     */
    private static final String tipo_bbdd = "sqlite";
    
    /**
     * Crea una conexión SQLite según las opciones especificadas
     * @param opciones Mapa con las opciones de configuración
     * @return Instancia de ConexionSqlite
     */
    public static ConexionSqlite crearConexion(Map<String, Object> opciones) {
        
        String tipoBase = (String) opciones.get("base");
        
        if (tipoBase == null || !tipoBase.toLowerCase().equals(tipo_bbdd)) {
            throw new IllegalArgumentException(String.format("'%s': formato no soportado", tipoBase));
        }
        
        try {
            return new ConexionSqlite(opciones);
        } catch (Exception err) {
            throw new RuntimeException("Error al crear la conexión SQLite", err);
        }
    }
}