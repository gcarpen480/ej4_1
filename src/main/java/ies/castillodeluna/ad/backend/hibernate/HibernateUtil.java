package ies.castillodeluna.ad.backend.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Clase que gestiona la conexión a la base de datos mediante Hibernate
 */
public class HibernateUtil {
    
    /**
     * Instancia única de SessionFactory
     */
    private static final SessionFactory sessionFactory = buildSessionFactory();

    /**
     * Método que construye las sesiones de Hibernate
     * @return SessionFactory Configuracion segun el archivo hibernate.cfg.xml
     */
    private static SessionFactory buildSessionFactory(){
        try {
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Obtenemos la instancia única de SessionFactory
     * @return SessionFactory
     */
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    /**
     * Cerramos la conexión a la base de datos
     */
    public static void shutdown(){
        getSessionFactory().close();
    }
}