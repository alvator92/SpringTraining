package ru.test.libapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.test.libapp.models.Item;
import ru.test.libapp.models.Person;

import java.util.List;

public class StartApp {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            Item item = session.get(Item.class, 3);
            System.out.println(item);

            Person person = item.getOwner();
            System.out.println(person);

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
