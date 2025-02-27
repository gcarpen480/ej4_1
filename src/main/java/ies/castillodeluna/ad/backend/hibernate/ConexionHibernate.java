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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCliente'");
    }

    @Override
    public Crud<Pedido> getPedido() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPedido'");
    }

    @Override
    public Crud<ZonaEnvio> getZonaEnvio() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getZonaEnvio'");
    }
    
}
