package ru.test.libapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.test.libapp.models.Person;

import java.util.List;

public class StartApp {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            session.createQuery("UPDATE Person SET name='Test' WHERE age < 44").executeUpdate();

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
