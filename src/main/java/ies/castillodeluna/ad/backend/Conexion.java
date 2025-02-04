package ies.castillodeluna.ad.backend;

import edu.acceso.sqlutils.dao.Crud;
import ies.castillodeluna.ad.models.Cliente;
import ies.castillodeluna.ad.models.Pedido;
import ies.castillodeluna.ad.models.ZonaEnvio;

/**
 * Interfaz que define los métodos necesarios para acceder a los diferentes DAO
 */
public interface Conexion {
   
   /**
    * Obtiene el DAO para la gestión de clientes
    */
    public Crud<Cliente> getCliente();

    /**
     * Obtiene el DAO para la gestión de pedidos
     */
    public Crud<Pedido> getPedido();

    /**
     * Obtiene el DAO para la gestión de zonas de envío
     */
    public Crud<ZonaEnvio> getZonaEnvio();
    
}
