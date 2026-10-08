package com.cristina.curso.springboot.jpa.springboot_jpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.cristina.curso.springboot.jpa.springboot_jpa.entities.Company;
import com.cristina.curso.springboot.jpa.springboot_jpa.entities.Person;
import com.cristina.curso.springboot.jpa.springboot_jpa.repositories.CompanyRepository;
import com.cristina.curso.springboot.jpa.springboot_jpa.repositories.PersonRepository;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository personRepository;

	@Autowired 
	private CompanyRepository companyRepository;

	

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//list();
		//findOne();
		//create();
		//update();
		//delete();
	}

	@Transactional 
	public void delete(){
		personRepository.findAll().forEach(System.out::println);


		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id de la persona a eliminar: ");
		Long id = scanner.nextLong();
		personRepository.deleteById(id);

		personRepository.findAll().forEach(System.out::println);
		

		scanner.close();
	}

	@Transactional 
	public void update(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id de la persona: ");
		Long id = scanner.nextLong();

		Optional <Person> optionalPerson = personRepository.findById(id);

		//optionalPerson.ifPresent(person -> {
		if(optionalPerson.isPresent()){
			Person person = optionalPerson.orElseThrow();

			System.out.println("Persona encontrada: " + person);
			System.out.println("Ingrese el lenguaje de programación: ");
			String programmingLanguage = scanner.next();
			person.setProgrammingLanguage(programmingLanguage);

			Person personDB = personRepository.save(person);
			System.out.println("Person updated: " + personDB);
		} else {
			System.out.println("Persona no encontrada!");
		}
		//});

		scanner.close();
	}

	@Transactional 
	public void create(){

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter name: ");
		String name = scanner.next();
		System.out.println("Enter last name: ");
		String lastName = scanner.next();
		System.out.println("Enter programming language: ");
		String programmingLanguage = scanner.next();
		scanner.close();

		Person person = new Person(null, name, lastName, programmingLanguage);

		//Person person = new Person(null ,"Lalo", "Thor", "Phyton");
		
		Person personNew = personRepository.save(person);
		System.out.println("Person created: " + personNew);

		// esto es una expresion lambda, es una manera de recorrer la lista de personas y mostrarla por consola
		//personRepository.findById(personNew.getId()).ifPresent(p -> System.out.println(p));

		// esto es una manera más simplificada de hacer lo mismo que el bloque anterior método de referencia
		personRepository.findById(personNew.getId()).ifPresent(System.out::println);



	}

	@Transactional(readOnly = true)
	public void findOne(){
		//Person person = personRepository.findById(1L).orElseThrow(() -> new RuntimeException("Person not found"));

		/*Person person = null;
		java.util.Optional<Person> optionalPerson = personRepository.findById(1L);
		if(optionalPerson.isPresent()){
			person = optionalPerson.get();
		}
			System.out.println(person);*/

		/*personRepository.findById(1L).ifPresent(person -> {
			System.out.println(person);
		});*/
		//una manera más simplificada de hacer lo mismo que el bloque anterior
		//personRepository.findById(1L).ifPresent(System.out::println);

		personRepository.findOne(1L).ifPresent(System.out::println);

		personRepository.findOneName("John").ifPresent(System.out::println);

		personRepository.findOneLikeName("ria").ifPresent(System.out::println);
		personRepository.findByNameContaining("se").ifPresent(System.out::println);

		
	}

	//este transactional es solo de lectura, no se va a modificar la base de datos, solo se va a leer
	@Transactional(readOnly = true)
	public void list(){
		//List<Person> persons = (List<Person>) personRepository.findAll();
		//List<Person> persons = (List<Person>) personRepository.findByProgrammingLanguage("JavaScript");
		List<Person> persons = (List<Person>) personRepository.buscarByProgrammingLanguageCustomQuery("Java", "Maria" );
		persons.forEach(person -> {
			System.out.println(person);

		});

		List<Company> companies = (List<Company>) companyRepository.findByName("Acme Corporation");
		companies.forEach(company -> {
			System.out.println(company);

		});

		List<Object[]> personData = personRepository.obtenerPersonData();
		personData.forEach(data -> {
			System.out.println("1." + data[0] + " es experto en: " + data[1]);
		});

		List<Object[]> personDataByProgrammingLanguage = personRepository.obtenerPersonDataByProgrammingLanguage("Java");
		personDataByProgrammingLanguage.forEach(data -> {
			System.out.println("2." + data[0] + " es experto en: " + data[1]);
		});

		List<Object[]> personDataByProgrammingLanguageAndName = personRepository.obtenerPersonData("Java", "Maria");
		personDataByProgrammingLanguageAndName.forEach(data -> {
			System.out.println("3." + data[0] + " es experto en: " + data[1]);
		});

		}

}
