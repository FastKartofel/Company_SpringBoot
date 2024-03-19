package com.example.ProjectSpring;

import com.example.ProjectSpring.models.Person;
import com.example.ProjectSpring.models.PersonType;
import com.example.ProjectSpring.interfaces.IPersonService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class NewPersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IPersonService personService;

    private List<Map<String,Object>> personMaps;

    @BeforeEach
    public void setUp() {
        personMaps = new ArrayList<>();

        Map<String, Object> personMap1 = new HashMap<>();
        personMap1.put("firstName", "John");
        personMap1.put("lastName", "Morales");
        personMap1.put("mobile", "555777888");
        personMap1.put("email", "moral.m@example.com");
        personMap1.put("pesel", "76045612378");
        personMap1.put("personType", PersonType.INTERNAL.toString());
        personMaps.add(personMap1);

        Map<String, Object> personMap2 = new HashMap<>();
        personMap2.put("firstName", "Anna");
        personMap2.put("lastName", "Zalewska");
        personMap2.put("mobile", "111222333");
        personMap2.put("email", "sandra.m@example.com");
        personMap2.put("pesel", "12245612378");
        personMap2.put("personType", PersonType.EXTERNAL.toString());
        personMaps.add(personMap2);

        Map<String, Object> personMap3 = new HashMap<>();
        personMap3.put("firstName", "Darek");
        personMap3.put("lastName", "Benek");
        personMap3.put("mobile", "123456789");
        personMap3.put("email", "bolek.m@example.com");
        personMap3.put("pesel", "99745612378");
        personMap3.put("personType", PersonType.INTERNAL.toString());
        personMaps.add(personMap3);
    }


    @Test
    public void testFindPerson() throws Exception {
        for(Map<String, Object> personMap : personMaps) {
            mockMvc.perform(post("/person")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(personMap)))
                    .andExpect(status().isCreated());
        }

        // find John Morales, he is located in the Internal catalog
        Map<String, Object> personMap = new HashMap<>();
        personMap.put("type", "INTERNAL");
        personMap.put("firstName", "John");
        personMap.put("lastName", "Morales");
        personMap.put("mobile", "555777888");

        mockMvc.perform(get("/person/find")
                        .param("type", personMap.get("type").toString())
                        .param("firstName", personMap.get("firstName").toString())
                        .param("lastName", personMap.get("lastName").toString())
                        .param("mobile", personMap.get("mobile").toString()))
                .andExpect(status().isOk())
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()));
    }

    @Test
    public void testRemovePerson() throws Exception {
        Map<String, Object> personMap = new HashMap<>();
        personMap.put("personId", 3);
        personMap.put("firstName", "Test");
        personMap.put("lastName", "User");
        personMap.put("mobile", "123456789");
        personMap.put("email", "test.user@example.com");
        personMap.put("pesel", "96045612378");
        personMap.put("personType", PersonType.EXTERNAL.toString());

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personMap)))
                .andExpect(status().isCreated());

        mockMvc.perform(delete("/person/remove/{personId}", 3))
                .andExpect(status().isOk())
                .andExpect(content().string("Person removed successfully!"));
    }

    @Test
    public void testModifyPerson() throws Exception { Map<String, Object> personMap = new HashMap<>(); personMap.put("firstName", "John"); personMap.put("lastName", "Morales"); personMap.put("mobile", "555777888"); personMap.put("email", "moral.m@example.com"); personMap.put("pesel", "96045612378"); personMap.put("personType", PersonType.INTERNAL);
        String response = mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personMap)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        Person person = objectMapper.readValue(response, Person.class);
        // modify John Morales to Jane Morales
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