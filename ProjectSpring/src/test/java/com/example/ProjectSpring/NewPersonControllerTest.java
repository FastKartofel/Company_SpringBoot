package com.example.ProjectSpring;

import com.example.ProjectSpring.models.Person;
import com.example.ProjectSpring.models.PersonType;
import com.example.ProjectSpring.interfaces.IPersonService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NewPersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IPersonService personService;

    @Test
    public void testFindPerson() throws Exception {
        Map<String, Object> personMap = new HashMap<>();
        personMap.put("firstName", "John");
        personMap.put("lastName", "Morales");
        personMap.put("mobile", "555777888");
        personMap.put("email", "moral.m@example.com");
        personMap.put("pesel", "96045612378");
        personMap.put("personType", PersonType.INTERNAL);
        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personMap)))
                .andExpect(status().isCreated());

        String response =mockMvc.perform(get("/person/find")
                        .param("type", "INTERNAL")
                        .param("firstName", "John")
                        .param("lastName", "Morales")
                        .param("mobile", "555777888"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("John")))
                .andExpect(jsonPath("$[0].lastName", is("Morales")))
                .andExpect(jsonPath("$[0].mobile", is("555777888")))
                .andReturn().getResponse().getContentAsString();

        List<Person> foundPeople = objectMapper.readValue(response, new TypeReference<List<Person>>() {});

        foundPeople.forEach(System.out::println);
    }

    @Test
    public void testRemovePerson() throws Exception {
        Map<String, Object> personMap = new HashMap<>();
        personMap.put("firstName", "John");
        personMap.put("lastName", "Doe");
        personMap.put("mobile", "555555555");
        personMap.put("email", "john.doe@example.com");
        personMap.put("pesel", "12345678901");
        personMap.put("personType", PersonType.INTERNAL);

        String response = mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personMap)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        Person person = objectMapper.readValue(response, Person.class);

        // Now try to remove the person
        mockMvc.perform(delete("/person/remove/{personId}", person.getPersonId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testModifyPerson() throws Exception { Map<String, Object> personMap = new HashMap<>(); personMap.put("firstName", "John"); personMap.put("lastName", "Morales"); personMap.put("mobile", "555777888"); personMap.put("email", "moral.m@example.com"); personMap.put("pesel", "96045612378"); personMap.put("personType", PersonType.INTERNAL);
        String response = mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personMap)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        Person person = objectMapper.readValue(response, Person.class);
        person.setFirstName("Jane");

        mockMvc.perform(put("/person/modify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/person/find")
                        .param("type", "INTERNAL")
                        .param("firstName", "Jane")
                        .param("lastName", "Morales")
                        .param("mobile", "555777888"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("Jane")));
    }
}