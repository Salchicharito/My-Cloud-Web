package org.gerdoc.util;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil
{
    private static final SessionFactory sessionFactory;

    static
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url  = System.getenv("DB_URL");
            String user = System.getenv("DB_USER");
            String pass = System.getenv("DB_PASSWORD");

            if (url == null || user == null || pass == null)
            {
                throw new IllegalStateException(
                        "Faltan las variables de entorno DB_URL, DB_USER o DB_PASSWORD");
            }

            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .setProperty("hibernate.connection.url", url)
                    .setProperty("hibernate.connection.username", user)
                    .setProperty("hibernate.connection.password", pass)
                    .buildSessionFactory();
        }
        catch (Throwable e)
        {
            System.err.println("Error al iniciar Hibernate: " + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}