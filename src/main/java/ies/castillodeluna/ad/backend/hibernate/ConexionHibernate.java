package ies.castillodeluna.ad.backend.hibernate;

import java.util.Map;

import edu.acceso.sqlutils.dao.Crud;
import edu.acceso.sqlutils.errors.DataAccessException;
import ies.castillodeluna.ad.backend.Conexion;
import ies.castillodeluna.ad.models.Cliente;
import ies.castillodeluna.ad.models.Pedido;
import ies.castillodeluna.ad.models.ZonaEnvio;

/**
 * Implementacion de la interfaz Conexion utilizando Hibernate
 */
public class ConexionHibernate implements Conexion {

    /**
     * Constructor que inicializa la conexión a Hibernate
     * @param opciones Mapa con opciones de configuración para la conexión 
     * @throws DataAccessException Lanzamos una excepción si hay errores al establecer la conexión con Hibernate
     */
    public ConexionHibernate(Map<String, Object> opciones) throws DataAccessException {
        try {

            HibernateUtil.getSessionFactory().openSession().close();
            
            System.out.println("Conexión a Hibernate establecida correctamente");
        } catch (Exception e) {
            throw new DataAccessException("Error al inicializar Hibernate: " + e.getMessage(), e);
        }
    }

    /**
     * Implementación de los métodos de la interfaz Conexion
     */
    @Override
    public Crud<Cliente> getCliente() {
        return new ClienteHibernate();
    }

    /**
     * Implementación de los métodos de la interfaz Conexion
     */
    @Override
    public Crud<Pedido> getPedido() {
        return new PedidoHibernate();
    }

    /**
     * Implementación de los métodos de la interfaz Conexion
     */
    @Override
    public Crud<ZonaEnvio> getZonaEnvio() {
        return new ZonaEnvioHibernate();
    }
}