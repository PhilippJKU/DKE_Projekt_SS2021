package com.example.demo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;

@Service
public class PersonService {



    @Autowired PersonRepository personRepository;

    public List<Person> getPersons() {
        return (List<Person>) personRepository.findAll();
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public Person deletePerson(Long id) {
        Optional<Person> person = personRepository.findById(id);
        personRepository.deleteById(id);
        Set<Person> persons = person.get().getPersons();
        persons.forEach(person1 -> {
            personRepository.deleteById(person1.getEntityId());
        });
        return person.get();

    }

    public Person getPersonById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.get();
    }

    public Person updatePerson(Long id, Person person) {
        Optional<Person> personOpt = personRepository.findById(id);
        Person personEntity = personOpt.get();
        updatePersonEntity(person, personEntity);
        return personRepository.save(personEntity);
    }

    private void updatePersonEntity(Person request, Person personEntity) {
        if(request.getName() != null && !request.getName().isEmpty())
            personEntity.setName(request.getName());

        if(request.getPersons() != null && !request.getPersons().isEmpty()){
            Set<Person> persons = new HashSet<>();
            persons.addAll(personEntity.getPersons());
            persons.addAll(request.getPersons());
            personEntity.setPersons(persons);
        }
    }

}
