package ies.castillodeluna.ad.backend.hibernate;

import java.util.Map;

import edu.acceso.sqlutils.dao.Crud;
import edu.acceso.sqlutils.errors.DataAccessException;
import ies.castillodeluna.ad.backend.Conexion;
import ies.castillodeluna.ad.models.Cliente;
import ies.castillodeluna.ad.models.Pedido;
import ies.castillodeluna.ad.models.ZonaEnvio;

public class ConexionHibernate implements Conexion{

    public ConexionHibernate(Map<String, Object> opciones) throws DataAccessException{
        // TODO Auto-generated constructor stub
    }

    @Override
    public Crud<Cliente> getCliente() {
        return new ClienteHibernate();
    }

    @Override
    public Crud<Pedido> getPedido() {
        return new PedidoHibernate();
    }

    @Override
    public Crud<ZonaEnvio> getZonaEnvio() {
        return new ZonaEnvioHibernate();
    }
    
}
