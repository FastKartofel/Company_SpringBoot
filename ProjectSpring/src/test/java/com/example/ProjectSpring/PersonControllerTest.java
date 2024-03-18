package com.example.ProjectSpring;

import com.example.ProjectSpring.models.PersonType;
import com.example.ProjectSpring.interfaces.IPersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IPersonService personService;

    @Test
    public void testAddPerson1() throws Exception {
        Map<String, Object> personMap = new HashMap<>();
        personMap.put("firstName", "Alex");
        personMap.put("lastName", "Johnson");
        personMap.put("mobile", "555123456");
        personMap.put("email", "alex.j@example.com");
        personMap.put("pesel", "91012345678");
        personMap.put("personType", PersonType.EXTERNAL);

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personMap)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAddPerson2() throws Exception {
        Map<String, Object> personMap = new HashMap<>();
        personMap.put("firstName", "Beatrice");
        personMap.put("lastName", "Smith");
        personMap.put("mobile", "555987654");
        personMap.put("email", "beatrice.s@example.com");
        personMap.put("pesel", "92045678901");
        personMap.put("personType", PersonType.EXTERNAL);

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personMap)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAddPerson3() throws Exception {
        Map<String, Object> personMap = new HashMap<>();
        personMap.put("firstName", "Charlie");
        personMap.put("lastName", "Brown");
        personMap.put("mobile", "555654321");
        personMap.put("email", "charlie.b@example.com");
        personMap.put("pesel", "93078912345");
        personMap.put("personType", PersonType.EXTERNAL);

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personMap)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAddPerson4() throws Exception {
        Map<String, Object> personMap = new HashMap<>();
        personMap.put("firstName", "Diana");
        personMap.put("lastName", "Wright");
        personMap.put("mobile", "555321987");
        personMap.put("email", "diana.w@example.com");
        personMap.put("pesel", "94032165498");
        personMap.put("personType", PersonType.INTERNAL);

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personMap)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAddPerson5() throws Exception {
        Map<String, Object> personMap = new HashMap<>();
        personMap.put("firstName", "Evan");
        personMap.put("lastName", "Grant");
        personMap.put("mobile", "555876543");
        personMap.put("email", "evan.g@example.com");
        personMap.put("pesel", "95012365478");
        personMap.put("personType", PersonType.EXTERNAL);

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personMap)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAddPerson6() throws Exception {
        Map<String, Object> personMap = new HashMap<>();
        personMap.put("firstName", "Fiona");
        personMap.put("lastName", "Miles");
        personMap.put("mobile", "555789012");
        personMap.put("email", "fiona.m@example.com");
        personMap.put("pesel", "96045612378");
        personMap.put("personType", PersonType.INTERNAL);

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personMap)))
                .andExpect(status().isCreated());
    }
    @Test
    public void testAddPerson7() throws Exception {
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
    }
}