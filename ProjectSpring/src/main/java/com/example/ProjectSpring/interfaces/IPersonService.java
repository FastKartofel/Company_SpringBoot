package com.example.ProjectSpring.interfaces;

import com.example.ProjectSpring.models.Person;
import com.example.ProjectSpring.models.PersonType;

import java.io.IOException;
import java.util.List;

public interface IPersonService {
    void savePerson(Person person) throws IOException;

    List<Person> find(PersonType personType, String firstName, String lastName, String mobile);

    String removePerson(int personId);

    void modify(Person person);
}
