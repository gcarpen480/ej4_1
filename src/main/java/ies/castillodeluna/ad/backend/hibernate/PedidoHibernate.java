package ies.castillodeluna.ad.backend.hibernate;

import java.util.Optional;
import java.util.stream.Stream;

import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.acceso.sqlutils.dao.Crud;
import edu.acceso.sqlutils.errors.DataAccessException;
import ies.castillodeluna.ad.models.Pedido;

public class PedidoHibernate implements Crud<Pedido> {

    @Override
    public boolean delete(int id) throws DataAccessException {

        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            transaction = session.beginTransaction();
            Pedido pedido = session.find(Pedido.class, id);

            if (pedido != null) {
                session.remove(pedido);
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
    public Stream<Pedido> get() throws DataAccessException {
        try {

            Session session = HibernateUtil.getSessionFactory().openSession();
            Stream<Pedido> result = session.createQuery("FROM Pedido", Pedido.class).stream();

            return result.onClose(() -> session.close());

        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public Optional<Pedido> get(int id) throws DataAccessException {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Pedido cliente = session.find(Pedido.class, id);
            return Optional.ofNullable(cliente);

        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public void insert(Pedido pedido) throws DataAccessException {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            session.persist(pedido);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                throw new DataAccessException(e);
            }
        }
    }

    @Override
    public boolean update(Pedido pedido) throws DataAccessException {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            session.merge(pedido);
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
            Pedido pedido = session.find(Pedido.class, oldId);

            if (pedido != null) {
                pedido.setId(newId);
                session.merge(pedido);
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
