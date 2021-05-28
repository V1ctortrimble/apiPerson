package com.api.brq.converter;

import com.api.brq.data.entity.PersonEntity;
import com.api.brq.dto.PersonDTO;
import com.api.brq.util.MapperUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonConverterTest {

    @InjectMocks
    private PersonConverter converter;

    @Mock
    private MapperUtil mapper;

    private PersonDTO dto;
    private PersonEntity entity;

    @Before
    public void setup(){
        when(mapper.use()).thenReturn(new ModelMapper());

        dto = PersonDTO.builder()
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
    }

    @Test
    public void toModel() {
        PersonDTO dto = converter.toDTO(entity);
        assertNotNull(dto);
    }

    @Test
    public void toModelNull() {
        PersonDTO dto = converter.toDTO(null);
        assertNull(dto);
    }

    @Test
    public void toEntity() {
        PersonEntity entity = converter.toEntity(dto);
        assertNotNull(entity);
    }

    @Test
    public void toEntityNull() {
        PersonEntity entity = converter.toEntity(null);
        assertNull(entity);
    }

    @Test
    public void toModelList() {
        List<PersonDTO> dtos = converter.toDTOList(Collections.singletonList(entity));
        assertNotNull(dtos);
        assertFalse(dtos.isEmpty());
    }

    @Test
    public void toModelListEmpty() {
        List<PersonDTO> models = converter.toDTOList(null);
        assertNotNull(models);
        assertTrue(models.isEmpty());
    }

    @Test
    public void toEntityList() {
        List<PersonEntity> entities = converter.toEntityList(Collections.singletonList(dto));
        assertNotNull(entities);
        assertFalse(entities.isEmpty());
    }

    @Test
    public void toEntityListEmpty() {
        List<PersonEntity> entities = converter.toEntityList(null);
        assertNotNull(entities);
        assertTrue(entities.isEmpty());
    }
}