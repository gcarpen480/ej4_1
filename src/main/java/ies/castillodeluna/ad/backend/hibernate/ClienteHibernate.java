package ies.castillodeluna.ad.backend.hibernate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.acceso.sqlutils.dao.Crud;
import edu.acceso.sqlutils.errors.DataAccessException;
import ies.castillodeluna.ad.models.Cliente;

public class ClienteHibernate implements Crud<Cliente> {

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

    @Override
    public Stream<Cliente> get() throws DataAccessException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            // Cargamos todos los clientes en una lista y cerramos la sesión inmediatamente
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

    @Override
    public Optional<Cliente> get(int id) throws DataAccessException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Cliente cliente = session.find(Cliente.class, id);
            return Optional.ofNullable(cliente);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

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