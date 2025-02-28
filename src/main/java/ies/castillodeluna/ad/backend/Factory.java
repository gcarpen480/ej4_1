package ies.castillodeluna.ad.backend;

import java.util.Map;

import ies.castillodeluna.ad.backend.hibernate.ConexionHibernate;

/**
 * Clase que implementa el patrón Factory para la creación de conexiones a la base de datos
 */
public class Factory {
    
    private static final String TIPO_BBDD_HIBERNATE = "hibernate";
    
    /**
     * Crea una conexión Hibernate según las opciones especificadas
     * @param opciones Mapa con las opciones de configuración
     * @return Instancia de la conexión a la base de datos
     */
    public static Conexion crearConexion(Map<String, Object> opciones) {
        String tipoBase = (String) opciones.get("base");
        
        if (tipoBase == null) {
            throw new IllegalArgumentException("Tipo de base de datos no especificado");
        }
        
        switch (tipoBase.toLowerCase()) {
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