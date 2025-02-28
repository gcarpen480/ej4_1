package ies.castillodeluna.ad.backend.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.acceso.sqlutils.dao.Crud;
import edu.acceso.sqlutils.errors.DataAccessException;
import ies.castillodeluna.ad.backend.hibernate.HibernateUtil;
import ies.castillodeluna.ad.models.ZonaEnvio;

/**
 * Implementación del DAO para ZonaEnvio utilizando Hibernate
 * Clase que sirve como adaptador entre la interfaz Crud y las operaciones de Hibernate
 */
public class ZonaEnvioAdapter implements Crud<ZonaEnvio> {
    
    /**
     * Implementación de los métodos CRUD
     */
    @Override
    public boolean delete(int id) throws DataAccessException {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            ZonaEnvio zonaEnvio = session.find(ZonaEnvio.class, id);

            if (zonaEnvio != null) {
                session.remove(zonaEnvio);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataAccessException(e);
        }
    }

    /**
     * Implementación de los métodos CRUD
     */
    @Override
    public Stream<ZonaEnvio> get() throws DataAccessException {

        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            List<ZonaEnvio> zonas = session.createQuery("FROM ZonaEnvio", ZonaEnvio.class).list();
            return zonas.stream();
        } catch (Exception e) {
            throw new DataAccessException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    /**
     * Implementación de los métodos CRUD
     */
    @Override
    public Optional<ZonaEnvio> get(int id) throws DataAccessException {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            ZonaEnvio zonaEnvio = session.find(ZonaEnvio.class, id);
            return Optional.ofNullable(zonaEnvio);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * Implementación de los métodos CRUD
     */
    @Override
    public void insert(ZonaEnvio zonaEnvio) throws DataAccessException {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(zonaEnvio);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataAccessException(e);
        }
    }

    /**
     * Implementación de los métodos CRUD
     */
    @Override
    public boolean update(ZonaEnvio zonaEnvio) throws DataAccessException {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(zonaEnvio);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataAccessException(e);
        }
    }

    /**
     * Implementación de los métodos CRUD
     */
    @Override
    public boolean update(int oldId, int newId) throws DataAccessException {
        
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            ZonaEnvio zonaEnvio = session.find(ZonaEnvio.class, oldId);

            if (zonaEnvio != null) {
                zonaEnvio.setId(newId);
                session.merge(zonaEnvio);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataAccessException(e);
        }
    }

}
