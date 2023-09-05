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

            Person person = session.get(Person.class, 5);

            List<Item> items = person.getItems();

            // SQL -  удаляет инфу из БД
            for(Item item : items) {
                session.remove(item);
            }

            // после удаления Item из таблицы необходимо удалить данные из КЭШ-а хибернайта
            person.getItems().clear();

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
