package com.cristina.curso.springboot.jpa.springboot_jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cristina.curso.springboot.jpa.springboot_jpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByProgrammingLanguage(String programmingLanguage);

    // Custom query using JPQL
    @Query("SELECT p FROM Person p where p.programmingLanguage = ?1 and p.name = ?2")
    List<Person> buscarByProgrammingLanguageCustomQuery(String programmingLanguage, String name);

    @Query("SELECT p.name, p.programmingLanguage FROM Person p")
    List<Object[]> obtenerPersonData();

     @Query ("SELECT p.name FROM Person p WHERE  p.name = ?1")
    List<Object[]> obtenerPersonData( String name);


    @Query ("SELECT p.name, p.programmingLanguage FROM Person p WHERE p.programmingLanguage = ?1 AND p.name = ?2")
    List<Object[]> obtenerPersonData(String programmingLanguage, String name);

     @Query ("SELECT p.name, p.programmingLanguage FROM Person p WHERE p.programmingLanguage = ?1")
    List<Object[]> obtenerPersonDataByProgrammingLanguage(String programmingLanguage);

    // Con estas consultas devolvemos un solo objeto, no una lista de objetos
    @Query ("SELECT p FROM Person p WHERE p.id = ?1")
    Optional<Person> findOne(Long id);

    @Query ("SELECT p FROM Person p WHERE p.name = ?1")
    Optional<Person> findOneName(String name);

    // Son dos maneras de hacer lo mismo, la primera es con JPQL y la segunda es con el método que nos ofrece Spring Data JPA
    @Query ("SELECT p FROM Person p WHERE p.name like %?1%")
    Optional<Person> findOneLikeName(String name);

    Optional<Person> findByNameContaining(String name);





}
