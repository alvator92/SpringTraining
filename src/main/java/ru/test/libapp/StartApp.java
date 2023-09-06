package ru.test.libapp;

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

            Movie newMovie = new Movie("Pulp Fiction", 1993);
            Actor actor1 = new Actor("Harvey", 81);
            Actor actor2 = new Actor("Samuel", 72);

            // Arrays.asList()
            newMovie.setActors(new ArrayList<>(List.of(actor1, actor2)));

            actor1.setMovies(new ArrayList<>(Collections.singletonList(newMovie)));
            actor2.setMovies(new ArrayList<>(Collections.singletonList(newMovie)));

            session.save(newMovie);

            session.save(actor1);
            session.save(actor2);

            session.getTransaction().commit();

        }
    }
}
