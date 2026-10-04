package com.cristina.curso.springboot.jpa.springboot_jpa.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cristina.curso.springboot.jpa.springboot_jpa.entities.Company;

public interface CompanyRepository extends CrudRepository<Company, Long> {
    

    List<Company> findByName(String name);


}
