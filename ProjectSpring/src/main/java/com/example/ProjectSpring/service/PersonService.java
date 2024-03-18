package com.example.ProjectSpring.service;

import com.example.ProjectSpring.models.Person;
import com.example.ProjectSpring.models.PersonType;
import com.example.ProjectSpring.interfaces.IPersonService;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements IPersonService {

    public List<Person> people = new ArrayList<>();

    @Override
    public void savePerson(Person person) throws IOException {
        people.add(person);
        String directoryPath = person.getPersonType() == PersonType.INTERNAL ? "Internal" : "External";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(directory, person.getPersonId() + ".xml");
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)))) {
            encoder.writeObject(person);
        }
    }

    @Override
    public List<Person> find(PersonType personType, String firstName, String lastName, String mobile) {
        return people.stream()
                .filter(person -> person.getPersonType() == personType)
                .filter(person -> person.getFirstName().equals(firstName))
                .filter(person -> person.getLastName().equals(lastName))
                .filter(person -> person.getMobile().equals(mobile))
                .collect(Collectors.toList());
    }

    @Override
    public boolean removePerson(int personId) {
        Person personToRemove = people.stream()
                .filter(person -> person.getPersonId() == personId)
                .findFirst()
                .orElse(null);

        if (personToRemove != null) {
            people.remove(personToRemove);

            String directoryPath = personToRemove.getPersonType() == PersonType.INTERNAL ? "Internal" : "External";
            File file = new File(directoryPath, personToRemove.getPersonId() + ".xml");
            if (file.exists()) {
                file.delete();
            }

            return true;
        }

        return false;
    }

    @Override
    public void modify(Person person) {
        for(int i =0; i< people.size(); i++){
            if(people.get(i).getPersonId() == person.getPersonId()){
                people.set(i, person);
                break;
            }
        }
    }
}