package ies.castillodeluna.ad.backend.hibernate;

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
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            transaction = session.beginTransaction();
            Cliente cliente = session.find(Cliente.class, id);

            if (cliente != null) {
                session.remove(cliente);
                transaction.commit();
                session.close();
                return true;
            }

            session.close();
            return false;
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            session.close();
            throw new DataAccessException(e);
        }
    }

    @Override
    public Stream<Cliente> get() throws DataAccessException {

        try {

            Session session = HibernateUtil.getSessionFactory().openSession();
            Stream<Cliente> result = session.createQuery("FROM Cliente", Cliente.class).stream();
            return result.onClose(() -> session.close());

        } catch (Exception e) {
            throw new DataAccessException(e);
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
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            transaction = session.beginTransaction();
            session.persist(cliente);
            transaction.commit();
            session.close();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            session.close();
            throw new DataAccessException(e);
        }

    }

    @Override
    public boolean update(Cliente cliente) throws DataAccessException {

        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            transaction = session.beginTransaction();
            session.merge(cliente);
            transaction.commit();
            session.close();
            return true;

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }
            session.close();
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