package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;

@RestController
@RequestMapping(value="/api/person")
public class PersonController {

    @Autowired
    private PersonService service;


    @GetMapping
    private List<Person> getAllPerson(){
        return service.getPersons();
    }

    @GetMapping("/{id}")
    private Person getPersonById(@PathVariable Long id){
        return service.getPersonById(id);
    }

    @PostMapping
    private Person savePerson(@RequestBody Person person){
        return service.savePerson(person);
    }

    @PatchMapping("/{id}")
    private Person updatePerson(@PathVariable Long id, @RequestBody Person person){
        return service.updatePerson(id, person);
    }

    @DeleteMapping("/{id}")
    private Person deletePerson(@PathVariable Long id){
        return service.deletePerson(id);
    }
}
