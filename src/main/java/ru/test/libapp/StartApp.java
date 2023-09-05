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

            Person person = session.get(Person.class, 4);

            Item newItem = new Item("Item from HIBERNATE", person);

            // Хорошим тоном считается когда отношение выстраивается с двух сторон
            // В КЕШ-е hibernate добавляется информация о том что мы добавили новый объект
            // Это гарантирует консистентность данных
            // Однако это никак не влияет на БД
            person.getItems().add(newItem);

            session.save(newItem);

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
