package ru.test.libapp;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.test.libapp.models.Actor;
import ru.test.libapp.models.Item;
import ru.test.libapp.models.Movie;
import ru.test.libapp.models.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StartApp {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Movie.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        //try with resources
        try (sessionFactory) {
            session.beginTransaction();

            Person person = session.get(Person.class, 6);
            System.out.println("Получили и Человека");

            System.out.println(person);

            // Специальный метод в Hibernate для того чтобы проициализировать поле объекта
            Hibernate.initialize(person.getItems());

            session.getTransaction().commit();

            System.out.println("вне сессии");
            // Теперь мы можем достать это поле в состояни Detached. После закрытия сессии
            System.out.println(person.getItems());

        }
    }
}
