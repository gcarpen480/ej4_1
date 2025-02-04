package ies.castillodeluna.ad.backend.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.stream.Stream;

import javax.sql.DataSource;

import edu.acceso.sqlutils.SqlUtils;
import edu.acceso.sqlutils.dao.Crud;
import edu.acceso.sqlutils.errors.DataAccessException;
import ies.castillodeluna.ad.models.ZonaEnvio;

/**
 * Implementación del DAO para ZonaEnvio
 */
public class ZonaEnvioSqlite implements Crud<ZonaEnvio>{

    /**
     * DataSource para la conexión a la base de datos
     */
    private DataSource ds;

    /**
     * 
     * @param ds
     */
    public ZonaEnvioSqlite(DataSource ds) {
        this.ds = ds;
    }

    /**
     * Metodo que convierte un ResultSet en un objeto ZonaEnvio
     * 
     * @param rs ResultSet con los datos de la ZonaEnvio
     * @param ds DataSource necesario para cargar las relaciones
     * @return Objeto ZonaEnvio con los datos del ResultSet
     * @throws SQLException Excepcion si hay errores al cargar las relaciones
     */
    private static ZonaEnvio resultToZonaEnvio(ResultSet rs , DataSource ds) throws SQLException {
        
        int id = rs.getInt("id_zona");
        String nombre = rs.getString("nombre_zona");
        double coste = rs.getDouble("tarifa_envio");

        return new ZonaEnvio(id, nombre, coste);

    }

    /**
     * Metodo que establece los parámetros de un PreparedStatement a partir de una ZonaEnvio
     * 
     * @param zonaEnvio
     * @param pstmt
     * @throws SQLException Excepcion si hay errores al cargar las relaciones
     */
    private static void setZonaEnvioParams(ZonaEnvio zonaEnvio, PreparedStatement pstmt) throws SQLException {
        
        pstmt.setInt(1, zonaEnvio.getId());
        pstmt.setString(2, zonaEnvio.getNombre());
        pstmt.setDouble(3, zonaEnvio.getCoste());
    }

    /**
     * Implementación de los métodos CRUD
     */
    @Override
    public Optional<ZonaEnvio> get(int id) throws DataAccessException {
        
        final String sqlString = "SELECT * FROM Zonas_Envio WHERE id_zona = ?";

        try (Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sqlString)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            return rs.next()?Optional.of(resultToZonaEnvio(rs, ds)):Optional.empty();
            
        } catch (SQLException err) {
            throw new DataAccessException(err);
        }
        
    }

    /**
     * Implementación de los métodos CRUD
     */
    @Override
    public Stream<ZonaEnvio> get() throws DataAccessException {
        
        final String sqlString = "SELECT * FROM Zonas_Envio";

        try {
            
            Connection conn = ds.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlString);

            return SqlUtils.resultSetToStream(conn, rs, fila -> resultToZonaEnvio(fila, ds));

        } catch (SQLException err) {
            throw new DataAccessException(err);
        }

    }
    
    /**
     * Implementación de los métodos CRUD
     */
    @Override
    public void insert(ZonaEnvio zona_envio) throws DataAccessException {
        
        final String sqlString = "INSERT INTO Zonas_Envio (id_zona, nombre_zona, tarifa_envio) VALUES (?, ?, ?)";

        try (Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sqlString) ) {

            setZonaEnvioParams(zona_envio, pstmt);
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
        
        final String sqlString = "DELETE FROM Zonas_Envio WHERE id_zona = ?";

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
    public boolean update(ZonaEnvio zona_envio) throws DataAccessException {
        
        final String sqlString = "UPDATE Zonas_Envio SET nombre_zona = ?, tarifa_envio = ? WHERE id_zona = ?";

        try (Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sqlString)) {
            
            pstmt.setString(1, zona_envio.getNombre());
            pstmt.setDouble(2, zona_envio.getCoste());
            pstmt.setInt(3, zona_envio.getId());

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
        
        final String sqlString = "UPDATE Zonas_Envio SET id_zona = ? WHERE id_zona = ?";

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
