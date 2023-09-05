package ru.test.libapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.test.libapp.models.Item;
import ru.test.libapp.models.Person;

import java.util.ArrayList;
import java.util.Collections;
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

            Person person = new Person("Name", 41, "newEm@mail.ee", "Russia, NSK, 123456");

            // owner side
            Item item = new Item("Ipad", person);

            // хорошая практика
            person.setItems(new ArrayList<>(Collections.singleton(item)));

            session.save(person);
            session.save(item);

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
