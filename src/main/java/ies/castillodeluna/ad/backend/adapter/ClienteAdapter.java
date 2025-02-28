package ies.castillodeluna.ad.backend.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.acceso.sqlutils.dao.Crud;
import edu.acceso.sqlutils.errors.DataAccessException;
import ies.castillodeluna.ad.backend.hibernate.HibernateUtil;
import ies.castillodeluna.ad.models.Cliente;

/**
 * Implementación del DAO para Cliente utilizando Hibernate
 * Clase que sirve como adaptador entre la interfaz Crud y las operaciones de Hibernate
 */
public class ClienteAdapter implements Crud<Cliente> {
    
    /**
     * Implementación de los métodos CRUD
     */
    @Override
    public boolean delete(int id) throws DataAccessException {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Cliente cliente = session.find(Cliente.class, id);

            if (cliente != null) {
                session.remove(cliente);
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
    public Stream<Cliente> get() throws DataAccessException {

        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            List<Cliente> clientes = session.createQuery("FROM Cliente", Cliente.class).list();
            return clientes.stream();
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
    public Optional<Cliente> get(int id) throws DataAccessException {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Cliente cliente = session.find(Cliente.class, id);
            return Optional.ofNullable(cliente);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * Implementación de los métodos CRUD
     */
    @Override
    public void insert(Cliente cliente) throws DataAccessException {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(cliente);
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
    public boolean update(Cliente cliente) throws DataAccessException {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(cliente);
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
            Cliente cliente = session.find(Cliente.class, oldId);

            if (cliente != null) {
                cliente.setId(newId);
                session.merge(cliente);
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
