package ru.test.libapp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.test.libapp.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDao {

    private SessionFactory sessionFactory;

    @Autowired
    public PersonDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // @Transactional - внутри метода происходит транзакция и закрывается в конце
    @Transactional(readOnly = true)
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();

        // Тут и будет обычный Hibernate код

        List<Person> people = session.createQuery("select p from person p", Person.class)
                .getResultList();

        return people;
    }

    public Optional<Person> show(String email) {
        return null;
    }

    public Person show(int id) {
        return null;
    }

    public void save(Person person) {

    }

    public void update(int id, Person updatedPerson) {

    }

    public void delete(int id) {

    }

}
