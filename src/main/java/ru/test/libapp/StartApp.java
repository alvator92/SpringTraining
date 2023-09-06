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

            // взяи только данные этого человека - без товаров которые он приобрел
            System.out.println(person);

            // закрыли сессию.
            session.getTransaction().commit();

            // для дальнейшего исползования надо взять новую сессиию
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            // можно использовать HQL
            List<Item> list = session.createQuery("select i from Item i where i.owner.id=:personId",
                    Item.class).setParameter("personId", person.getId()).getResultList();

            //инициализируем список который у нас не был ранее подгружен из-за LAZY
            Hibernate.initialize(person.getItems());

            session.getTransaction().commit();

            System.out.println("вне сессии");
            // Теперь мы можем достать это поле в состояни Detached. После закрытия второй сессии
            System.out.println(person.getItems());

        }
    }
}
