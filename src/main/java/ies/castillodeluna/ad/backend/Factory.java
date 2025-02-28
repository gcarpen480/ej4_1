package ies.castillodeluna.ad.backend;

import java.util.Map;

import ies.castillodeluna.ad.backend.adapter.ConexionAdapter;
import ies.castillodeluna.ad.backend.sqlite.ConexionSqlite;

/**
 * Clase Factory encargada de crear conexiones con SQLite
 */
public class Factory{
    
    /**
     * Tipos de bases de datos soportada
     */
    private static final String tipo_bbdd_sqlite = "sqlite";
    private static final String tipo_bbdd_hibernate = "hibernate";
    
    /**
     * Crea una conexión SQLite según las opciones especificadas
     * @param opciones Mapa con las opciones de configuración
     * @return Instancia de ConexionSqlite
     */
    public static Conexion crearConexion(Map<String, Object> opciones) {
        
        String tipoBase = (String) opciones.get("base");
        
        if (tipoBase == null) {
            throw new IllegalArgumentException("Tipo de base de datos no especificado");
        }
        
        switch (tipoBase.toLowerCase()) {
            case tipo_bbdd_sqlite:
                try {
                    return new ConexionSqlite(opciones);
                } catch (Exception e) {
                    throw new RuntimeException("Error creando conexión SQLite", e);
                }
            case tipo_bbdd_hibernate:
                try {
                    return new ConexionAdapter(opciones);
                } catch (Exception e) {
                    throw new RuntimeException("Error creando conexión Hibernate", e);
                }
            default:
                throw new IllegalArgumentException(String.format("'%s': formato no soportado", tipoBase));
        }
    }
}