package ies.castillodeluna.ad.backend.adapter;

import java.util.Map;

import edu.acceso.sqlutils.dao.Crud;
import edu.acceso.sqlutils.errors.DataAccessException;
import ies.castillodeluna.ad.backend.Conexion;
import ies.castillodeluna.ad.backend.hibernate.HibernateUtil;
import ies.castillodeluna.ad.models.Cliente;
import ies.castillodeluna.ad.models.Pedido;
import ies.castillodeluna.ad.models.ZonaEnvio;

public class ConexionAdapter implements Conexion{
    
    public ConexionAdapter(Map<String, Object> opciones) throws DataAccessException {
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
        return new ClienteAdapter();
    }

    @Override
    public Crud<Pedido> getPedido() {
        return new PedidoAdapter();
    }

    @Override
    public Crud<ZonaEnvio> getZonaEnvio() {
        return new ZonaEnvioAdapter();
    }

}
