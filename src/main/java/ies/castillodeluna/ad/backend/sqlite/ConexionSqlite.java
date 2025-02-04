package ies.castillodeluna.ad.backend.sqlite;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.stream.Stream;

import org.sqlite.javax.SQLiteConnectionPoolDataSource;

import edu.acceso.sqlutils.SqlUtils;
import edu.acceso.sqlutils.dao.Crud;
import edu.acceso.sqlutils.errors.DataAccessException;
import ies.castillodeluna.ad.backend.Conexion;
import ies.castillodeluna.ad.models.Cliente;
import ies.castillodeluna.ad.models.Pedido;
import ies.castillodeluna.ad.models.ZonaEnvio;


/**
 * Clase que gestiona la conexión y proporciona acceso a los DAOs
 */
public class ConexionSqlite implements Conexion{

    /**
     * Ruta al archivo de la base de datos SQLite
     */
    final static Path ruta = Path.of(System.getProperty("user.dir"), "src", "main", "resources", "pedidos.sql");

    /**
     * Protocolo de conexión para SQLite
     */
    final static String protocolo = "jdbc:sqlite:";

    /**
     * URL completa de conexión a la base de datos
     */
    final static String urlDB = protocolo + ruta.toString();

    private String url;

    /**
     * DataSource específico para SQLite
     */
    private final SQLiteConnectionPoolDataSource ds = new SQLiteConnectionPoolDataSource();

    /**
     * @param opciones Mapa con opciones de configuración
     */
    public ConexionSqlite(Map<String, Object> opciones) throws DataAccessException {
        url = (String) opciones.get("url");
        ds.setUrl(url);
        initBD();
    }

    /**
     * Metodo que inicializa el esquema de la base de datos si no existe
     */
    private void initBD() throws DataAccessException {
        try (Stream<Cliente> clientes = getCliente().get()) {
            clientes.close();
        } catch (DataAccessException err) {
            esquema();
        }
    }

    /**
     * Metodo para crear el esquema inicial de la base de datos
     */
    private void esquema() throws DataAccessException {

        try (InputStream schemma = Files.newInputStream(ruta);

                Connection conn = ds.getConnection()) {
                SqlUtils.executeSQL(conn, schemma);

        } catch (SQLException e) {
            
            throw new DataAccessException("No se pudo crear el esquema de la base de datos", e);
        } catch (IOException e) {

            throw new DataAccessException(String.format("No se pudo acceder al esquema: %s", ruta), e);
        }
    }

    /**
     * Implementación de los métodos de la interfaz Conexion
     */
    @Override
    public Crud<Cliente> getCliente() {
        return new ClienteSqlite(ds);
    }

    /**
     * Implementación de los métodos de la interfaz Conexion
     */
    @Override
    public Crud<Pedido> getPedido() {
       return new PedidoSqlite(ds);
    }

    /**
     * Implementación de los métodos de la interfaz Conexion
     */
    @Override
    public Crud<ZonaEnvio> getZonaEnvio() {
        return new ZonaEnvioSqlite(ds);
    }

    

}
