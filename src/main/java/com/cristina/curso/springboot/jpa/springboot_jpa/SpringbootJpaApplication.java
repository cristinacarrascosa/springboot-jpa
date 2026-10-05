package com.cristina.curso.springboot.jpa.springboot_jpa;

import java.util.List;

import org.hibernate.internal.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		findOne();
	}

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
		personRepository.findById(1L).ifPresent(System.out::println);
	}

	
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
