package com.api.brq.controller;

import com.api.brq.data.entity.PersonEntity;
import com.api.brq.dto.PersonDTO;
import com.api.brq.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersonService service;

    private PersonEntity entity;
    private final List<PersonEntity> entities = new ArrayList<>();

    @Before
    public void setup(){

        entity = PersonEntity.builder()
                .birthDate(LocalDate.now())
                .celPhone("999999")
                .cep("86088032")
                .cpf("1176543209")
                .idPerson(1)
                .email("email@teste.com")
                .lastName("LastNameTeste")
                .name("nameTeste")
                .phone("3333333")
                .rg("14323455")
                .build();

        entities.add(entity);
    }

    @Test
    public void getAllPersonsIsOk() throws Exception {
        when(service.getAllPersons()).thenReturn(entities);
        this.mvc.perform(get("/api/persons/get"))
                .andExpect(status().isOk());
    }

    @Test
    public void getPersonByIdIsOk() throws Exception {
        when(service.getPersonById(1)).thenReturn(entity);
        this.mvc.perform(get("/api/persons/getbyid")
                .param("id", String.valueOf(1)))
                .andExpect(status().isOk());
    }

    @Test
    public void getPersonByIdIsBadRequest() throws Exception {
        when(service.getPersonById(2)).thenReturn(null);
        this.mvc.perform(get("/api/persons/getbyid")
                .param("id", String.valueOf(2)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void postPersonIsOk() throws Exception {
        verify(service, times(0)).postPerson(entity);
        mvc.perform(post("/api/person/post").contentType(APPLICATION_JSON)
                .content("{\n" +
                        "  \"birth_date\": \"2000-06-13\",\n" +
                        "  \"cell_phone\": \"(43)99906-5136\",\n" +
                        "  \"cep\": \"86088040\",\n" +
                        "  \"cpf\": \"11867136902\",\n" +
                        "  \"email\": \"victorif627@outlook.com\",\n" +
                        "  \"id_person\": 0,\n" +
                        "  \"last_name_person\": \"Santos\",\n" +
                        "  \"name_person\": \"Victor\",\n" +
                        "  \"phone\": \"(43)3329-4933\",\n" +
                        "  \"rg\": \"142206021\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    public void postPersonIsBadRequest() throws Exception {
        verify(service, times(0)).postPerson(null);
        mvc.perform(post("/api/person/post").contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void putPersonIsNotFound() throws Exception {
        verify(service, times(0)).postPerson(entity);
        mvc.perform(put("/api/person/put").contentType(APPLICATION_JSON)
                .content("{\n" +
                        "  \"birth_date\": \"2000-06-13\",\n" +
                        "  \"cell_phone\": \"(43)99906-5136\",\n" +
                        "  \"cep\": \"86088040\",\n" +
                        "  \"cpf\": \"11867136902\",\n" +
                        "  \"email\": \"victorif627@outlook.com\",\n" +
                        "  \"id_person\": 0,\n" +
                        "  \"last_name_person\": \"Santos\",\n" +
                        "  \"name_person\": \"Victor\",\n" +
                        "  \"phone\": \"(43)3329-4933\",\n" +
                        "  \"rg\": \"142206021\"\n" +
                        "}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void putPersonIsBadRequest() throws Exception {
        verify(service, times(0)).postPerson(entity);
        mvc.perform(put("/api/person/put").contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}