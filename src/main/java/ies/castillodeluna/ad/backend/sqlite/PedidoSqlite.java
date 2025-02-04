package ies.castillodeluna.ad.backend.sqlite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

import javax.sql.DataSource;

import edu.acceso.sqlutils.SqlUtils;
import edu.acceso.sqlutils.dao.Crud;
import edu.acceso.sqlutils.errors.DataAccessException;
import ies.castillodeluna.ad.models.Cliente;
import ies.castillodeluna.ad.models.Pedido;

/**
 * Implementación del DAO para Pedido
 */
public class PedidoSqlite implements Crud<Pedido>{

    /**
     * DataSource para la conexión a la base de datos
     */
    private DataSource ds;

    /**
     * 
     * @param ds
     */
    public PedidoSqlite(DataSource ds) {
        this.ds = ds;
    }

    /**
     * Metodo que convierte un ResultSet en un objeto Pedido
     * 
     * @param rs ResultSet con los datos del pedido
     * @param ds DataSource necesario para cargar las relaciones
     * @return Objeto Pedido con los datos del ResultSet
     * @throws SQLException Excepcion si hay errores al acceder a los datos
     * @throws DataAccessException Excepcion si hay errores al cargar las relaciones
     */
    private static Pedido resultToPedido(ResultSet rs , DataSource ds) throws SQLException, DataAccessException{

        int id = rs.getInt("id_pedido");
        LocalDate fecha = rs.getDate("fecha").toLocalDate();
        double importe = rs.getDouble("importe_total");
        Integer id_cliente = rs.getInt("id_cliente");

        return new Pedido(id, fecha, importe, new ClienteSqlite(ds).get(id_cliente).orElse(null));
    }

    /**
     * Metodo que establece los parámetros de un PreparedStatement a partir de un Pedido
     * 
     * @param pedido
     * @param pstmt
     * @throws SQLException Excepcion si hay errores al acceder a los datos
     */
    private static void setPedidoParams(Pedido pedido, PreparedStatement pstmt) throws SQLException {

        Cliente cliente = pedido.getId_cliente();

        pstmt.setInt(1, pedido.getId());
        pstmt.setDate(2, Date.valueOf(pedido.getFecha()));
        pstmt.setDouble(3, pedido.getImporte());
        pstmt.setObject(4, cliente == null ? cliente : cliente.getId(), Types.INTEGER);
    }

    /**
     * Implementación de los métodos CRUD
     */
    @Override
    public Stream<Pedido> get() throws DataAccessException {
        
        final String sqlString = "SELECT * FROM Pedidos";

        try {
            
            Connection conn = ds.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlString);

            return SqlUtils.resultSetToStream(conn, rs , fila -> {
                try {
                    return resultToPedido(fila, ds);
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
    public Optional<Pedido> get(int id) throws DataAccessException {
        
        final String sqlString = "SELECT * FROM pedido WHERE id_pedido = ?";

        try (Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sqlString)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            return rs.next()?Optional.of(resultToPedido(rs,ds)):Optional.empty();
            
        } catch (SQLException err) {
            throw new DataAccessException(err);
        }

    }

    /**
     * Implementación de los métodos CRUD
     */
    @Override
    public void insert(Pedido pedido) throws DataAccessException {
        
        final String sqlString = "INSERT INTO Pedidos (id_pedido, fecha, importe_total, id_cliente) VALUES (?, ?, ?, ?)";

        try (Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sqlString)) {
            
            setPedidoParams(pedido, pstmt);
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
    
        final String sqlString = "DELETE FROM Pedidos WHERE id_pedido = ?";
        
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
    public boolean update(Pedido pedido) throws DataAccessException {
        
        final String sqlString = "UPDATE Pedidos SET fecha = ?, importe_total = ?, id_cliente = ? WHERE id_pedido = ?";

        try (Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sqlString)) {

            pstmt.setDate(1, Date.valueOf(pedido.getFecha()));
            pstmt.setDouble(2, pedido.getImporte());
            pstmt.setObject(3, pedido.getId_cliente().getId());
            pstmt.setInt(4, pedido.getId());

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
        
        final String sqlString = "UPDATE Pedidos SET id_pedido = ? WHERE id_pedido = ?";

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
