package ru.test.libapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.test.libapp.models.Person;

public class StartApp {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            Person person = session.get(Person.class, 1);

            person.setName("Vlad");

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
