package com.example.ProjectSpring.controllers;

import com.example.ProjectSpring.models.Person;
import com.example.ProjectSpring.interfaces.IPersonService;
import com.example.ProjectSpring.models.PersonType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final IPersonService personService;

    public PersonController(IPersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<Person> savePerson(@RequestBody Person person) {
        try {
            personService.savePerson(person);
            return new ResponseEntity<>(person, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find")
    public ResponseEntity<List<Person>> findPerson(@RequestParam PersonType type, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String mobile) {
        List<Person> foundPeople = personService.find(type, firstName, lastName, mobile);
        return new ResponseEntity<>(foundPeople, HttpStatus.OK);
    }


    @DeleteMapping("/remove/{personId}")
    public ResponseEntity<String> removePerson(@PathVariable Integer personId) {
        boolean isRemoved = personService.removePerson(personId);
        if (isRemoved) {
            return new ResponseEntity<>("Person removed successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Person not found!", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/modify")
    public ResponseEntity<String> modifyPerson(@RequestBody Person person) {
        personService.modify(person);
        return new ResponseEntity<>("Person modified successfully!", HttpStatus.OK);
    }
}