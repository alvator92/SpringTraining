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

            List<Person> personList = session.createQuery("FROM Person WHERE age > 33").getResultList();
            personList.forEach(System.out::println);

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
