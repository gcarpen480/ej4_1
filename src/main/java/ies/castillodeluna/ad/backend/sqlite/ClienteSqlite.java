package ies.castillodeluna.ad.backend.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Optional;
import java.util.stream.Stream;

import javax.sql.DataSource;

import edu.acceso.sqlutils.SqlUtils;
import edu.acceso.sqlutils.dao.Crud;
import edu.acceso.sqlutils.errors.DataAccessException;
import ies.castillodeluna.ad.models.Cliente;
import ies.castillodeluna.ad.models.ZonaEnvio;

/**
 * Implementación del DAO para Cliente
 */
public class ClienteSqlite implements Crud<Cliente>{

    /**
     * DataSource para la conexión a la base de datos
     */
    private DataSource ds;

    /**
     * 
     * @param ds
     */
    public ClienteSqlite(DataSource ds) {
        this.ds = ds;
    }

    /**
     * Metodo que convierte un ResultSet en un objeto Cliente
     * 
     * @param rs ResultSet con los datos del cliente
     * @param ds DataSource necesario para cargar las relaciones
     * @return Objeto Cliente con los datos del ResultSet
     * @throws SQLException Excepcion si hay errores al acceder a los datos
     * @throws DataAccessException Excepcion si hay errores al cargar las relaciones
     */
    private static Cliente resultToCliente(ResultSet rs , DataSource ds) throws SQLException, DataAccessException {

        int id = rs.getInt("id_cliente");
        String nombre = rs.getString("nombre");
        String email = rs.getString("email");
        String telefono = rs.getString("telefono");
        Integer id_zona = rs.getInt("id_zona");

        return new Cliente(id, nombre, email, telefono, new ZonaEnvioSqlite(ds).get(id_zona).orElse(null));
        
    }

    /**
     * Metodo que establece los parámetros de un PreparedStatement a partir de un Cliente
     * 
     * @param cliente
     * @param pstmt
     * @throws SQLException Excepcion si hay errores al acceder a los datos
     */
    private static void setClienteParams(Cliente cliente, PreparedStatement pstmt) throws SQLException {

        ZonaEnvio zona_Envio = cliente.getId_zona();

        pstmt.setInt(1, cliente.getId());
        pstmt.setString(2, cliente.getNombre());
        pstmt.setString(3, cliente.getEmail());
        pstmt.setString(4, cliente.getTelefono());
        pstmt.setObject(5, zona_Envio == null ? zona_Envio : zona_Envio.getId(), Types.INTEGER);
    }
    
    /**
     * Implementación de los métodos CRUD
     */
    @Override
    public Stream<Cliente> get() throws DataAccessException {
        
        final String sqlString = "SELECT * FROM Clientes";

        try {
            
            Connection conn = ds.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlString);

            return SqlUtils.resultSetToStream(conn, rs, fila -> {
                try {
                    return resultToCliente(fila, ds);
                } catch (DataAccessException err) {
                    err.printStackTrace();
                }
                return null;
            });

        } catch (SQLException err) {
            throw new DataAccessException(err);
        }

    }

    /**
     * Implementación de los métodos CRUD
     */
    @Override
    public Optional<Cliente> get(int id) throws DataAccessException {
        
        final String sqlString = "SELECT * FROM Clientes WHERE id_cliente = ?";

        try (Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sqlString)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            return rs.next()?Optional.of(resultToCliente(rs, ds)):Optional.empty();

        } catch (SQLException err) {
            throw new DataAccessException(err);
        }

    }

    /**
     * Implementación de los métodos CRUD
     */
    @Override
    public void insert(Cliente cliente) throws DataAccessException {
        
        final String sqlString = "INSERT INTO Clientes (id_cliente, nombre, email, telefono, id_zona) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sqlString)) {
            
            setClienteParams(cliente, pstmt);
            pstmt.executeUpdate();

        } catch (SQLException err) {
            throw new DataAccessException(err);

        }
    }

    /**
     * Implementación de los métodos CRUD
     */
    @Override
    public boolean delete(int id) throws DataAccessException {
        
        final String sqlString = "DELETE FROM Clientes WHERE id_cliente = ?";

        try (Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sqlString)) {
            
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException err) {
            throw new DataAccessException(err);
        }

    }

    /**
     * Implementación de los métodos CRUD
     */
    @Override
    public boolean update(Cliente cliente) throws DataAccessException {
        
        final String sqlString = "UPDATE Clientes SET nombre = ?, email = ?, telefono = ?, id_zona = ? WHERE id_cliente = ?";

        try (Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sqlString)) {
            
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getEmail());
            pstmt.setString(3, cliente.getTelefono());

            if (cliente.getId_zona() != null) {
                pstmt.setObject(4, cliente.getId_zona().getId(), Types.INTEGER);
            } else {
                pstmt.setNull(4, Types.INTEGER);
                
            }

            pstmt.setInt(5, cliente.getId());
            return pstmt.executeUpdate() > 0;

        } catch (SQLException err) {
            throw new DataAccessException(err);
        }

    }

    /**
     * Implementación de los métodos CRUD
     */
    @Override
    public boolean update(int oldId, int newId) throws DataAccessException {
        
        final String sqlString = "UPDATE Clientes SET id_cliente = ? WHERE id_cliente = ?";

        try (Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sqlString)) {
            
            pstmt.setInt(1, newId);
            pstmt.setInt(2, oldId);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException err) {
            throw new DataAccessException(err);

        }
    }
}
