package ies.castillodeluna.ad.backend.hibernate;

import java.util.Map;

import edu.acceso.sqlutils.dao.Crud;
import edu.acceso.sqlutils.errors.DataAccessException;
import ies.castillodeluna.ad.backend.Conexion;
import ies.castillodeluna.ad.models.Cliente;
import ies.castillodeluna.ad.models.Pedido;
import ies.castillodeluna.ad.models.ZonaEnvio;

public class ConexionHibernate implements Conexion {

    public ConexionHibernate(Map<String, Object> opciones) throws DataAccessException {
        try {
            // Verificar la conexión a Hibernate
            HibernateUtil.getSessionFactory().openSession().close();
            
            System.out.println("Conexión a Hibernate establecida correctamente");
        } catch (Exception e) {
            throw new DataAccessException("Error al inicializar Hibernate: " + e.getMessage(), e);
        }
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