package com.cristina.curso.springboot.jpa.springboot_jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cristina.curso.springboot.jpa.springboot_jpa.entities.Person;

import dto.PersonDto;

public interface PersonRepository extends CrudRepository<Person, Long> {

    @Query ("SELECT p.id, upper(p.name), lower(p.lastName), upper(p.programmingLanguage) FROM Person p ")
    List<Object[]>findPersonDataListCase();

    @Query ("SELECT upper(CONCAT(p.name, ' ', p.lastName)) FROM Person p")
    List<String> findAllFullNamesUpper();

    @Query ("SELECT lower (p.name || ' ' ||p.lastName) FROM Person p")
    List<String> findAllFullNamesLower();

    //@Query ("SELECT CONCAT(p.name, ' ', p.lastName) FROM Person p")
    @Query ("SELECT p.name || ' ' ||p.lastName FROM Person p")
    List<String> findAllFullNames();

    @Query ("SELECT COUNT(DISTINCT p.programmingLanguage) FROM Person p")
    List<Long> findAllDistinctCountProgrammingLanguages();

    @Query ("SELECT DISTINCT p.programmingLanguage FROM Person p")
    List<String> findAllDistinctProgrammingLanguages();

    @Query ("SELECT p.name FROM Person p")
    List<String> findAllNames();

    @Query ("SELECT DISTINCT p.name FROM Person p")
    List<String> findAllDistinctNames();

    @Query ("SELECT new dto.PersonDto(p.name, p.lastName) FROM Person p ")
    List<PersonDto> findAllPersonDto();


    List<Person> findByProgrammingLanguage(String programmingLanguage);

    // Este es a partir del contructor que hemos creado en la clase Person, y nos devuelve un objeto de tipo Person
    @Query ("SELECT new Person(p.name, p.lastName) FROM Person p ")
    List<Person> findAllObjectPersonPersonalized();

    @Query ("SELECT p, p.programmingLanguage FROM Person p ")
    List<Object[]> findAllMixPerson();

    @Query ("SELECT p.id, p.name, p.lastName, p.programmingLanguage FROM Person p ")
    List<Object[]> obtenerPersonDataList();

    @Query ("SELECT p.id, p.name, p.lastName, p.programmingLanguage FROM Person p WHERE p.id = ?1")
    //Object obtenerPersonDataById(Long id);
    Optional<Object> obtenerPersonDataById(Long id);

    // Saca el nombre de la persona a partir del id, pero no devuelve el objeto completo, sino solo el nombre
    @Query ("SELECT p.name From Person p WHERE p.id = ?1")
    String getNameById(Long id);

    // Saca el id de la persona a partir del id, pero no devuelve el objeto completo, sino solo el id
    @Query ("SELECT p.id From Person p WHERE p.id = ?1")
    Long getIdById(Long id);

    // Saca el nombre completo de la persona a partir del id, pero no devuelve el objeto completo, sino solo el nombre completo
    @Query ("SELECT CONCAT(p.name, ' ', p.lastName) AS fullName From Person p WHERE p.id = ?1")
    String getFullNameById(Long id);

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
