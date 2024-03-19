package com.example.ProjectSpring.models;

import com.example.ProjectSpring.IdGenerator;

import java.util.concurrent.atomic.AtomicInteger;

public class Person {
    // Static counter to ensure unique ID generation
    private static final IdGenerator ID_GENERATOR = new IdGenerator();
    private Integer personId;
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    private String pesel;

    private PersonType personType;

    public Person() {
        this.personId = ID_GENERATOR.generateId();
    }

    // Constructor without personId as it's auto-generated
    public Person(String firstName, String lastName, String mobile, String email, String pesel, PersonType personType){
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.pesel = pesel;
        this.personType = personType;
    }

    public int getPersonId() {
        if(personId == null){
            personId = ID_GENERATOR.generateId();
        }
        return personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", pesel='" + pesel + '\'' +
                ", personType=" + personType +
                '}';
    }
}
