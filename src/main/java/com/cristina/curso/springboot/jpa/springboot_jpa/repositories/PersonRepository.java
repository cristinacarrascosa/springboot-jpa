package com.cristina.curso.springboot.jpa.springboot_jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cristina.curso.springboot.jpa.springboot_jpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByProgrammingLanguage(String programmingLanguage);

    // Custom query using JPQL
    @Query("SELECT p FROM Person p")
    List<Person> buscarByProgrammingLanguageCustomQuery(String programmingLanguage);





}
