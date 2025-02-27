package ies.castillodeluna.ad.backend;

import java.util.Map;

import ies.castillodeluna.ad.backend.hibernate.ConexionHibernate;

public class Factory {
    
    // private static final String TIPO_BBDD_SQLITE = "sqlite";
    private static final String TIPO_BBDD_HIBERNATE = "hibernate";
    
    public static Conexion crearConexion(Map<String, Object> opciones) {
        String tipoBase = (String) opciones.get("base");
        
        if (tipoBase == null) {
            throw new IllegalArgumentException("Tipo de base de datos no especificado");
        }
        
        switch (tipoBase.toLowerCase()) {
            // case TIPO_BBDD_SQLITE:
            //     try {
            //         return new ConexionSqlite(opciones);
            //     } catch (Exception e) {
            //         throw new RuntimeException("Error creando conexión SQLite", e);
            //     }
            case TIPO_BBDD_HIBERNATE:
                try {
                    return new ConexionHibernate(opciones);
                } catch (Exception e) {
                    throw new RuntimeException("Error creando conexión Hibernate", e);
                }
            default:
                throw new IllegalArgumentException(String.format("'%s': formato no soportado", tipoBase));
        }
    }
}