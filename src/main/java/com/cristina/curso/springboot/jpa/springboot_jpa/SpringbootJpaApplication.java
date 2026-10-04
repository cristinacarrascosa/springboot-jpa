package com.cristina.curso.springboot.jpa.springboot_jpa;

import java.util.List;

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

		
	}

}
