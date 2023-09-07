package ru.test.libapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.test.libapp.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
}