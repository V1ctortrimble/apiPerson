package com.api.brq.controller;

import com.api.brq.converter.PersonConverter;
import com.api.brq.data.entity.PersonEntity;
import com.api.brq.dto.PersonDTO;
import com.api.brq.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"visualization of Person"})
@RestController
@RequestMapping(value = "api")
@RequiredArgsConstructor
public class PersonController {

    /**
     * Contants
     */
    public static final String FAILED_TO_INSERT_NEW_PERSON = "Failed to Insert New Person!";
    public static final String FAILED_TO_UPDATE_PERSON = "Failed to Update Person!";
    public static final String FAILED_TO_GET_PERSON_BY_ID = "Failed to Get Person By Id!";
    public static final String FAILED_TO_DELETE_PERSON_BY_ID = "Failed to Delete Person By Id!";

    /**
     * Service
     */
    private final PersonService service;

    /**
     * Converter
     */
    private final PersonConverter converter;

    @ApiOperation(value = "URL To Returns All Persons")
    @GetMapping(path = "/persons/get")
    public ResponseEntity<List<PersonDTO>> getAllPersons() {
        List<PersonEntity> entities = service.getAllPersons();
        return new ResponseEntity<>(converter.toDTOList(entities), HttpStatus.OK);
    }

    @ApiOperation(value = "URL To Returns Person By Id")
    @GetMapping(path = "/persons/getbyid")
    public ResponseEntity<PersonDTO> getPersonById(@Validated @RequestParam Integer id) throws Exception {
        PersonEntity entity = service.getPersonById(id);
        if (entity != null){
            return new ResponseEntity<>(converter.toDTO(entity), HttpStatus.OK);
        }
        throw new Exception(FAILED_TO_GET_PERSON_BY_ID);
    }

    @ApiOperation(value = "URL To Post Of Persons")
    @PostMapping(path = "/person/post")
    public ResponseEntity<PersonDTO> postPerson(@Validated @RequestBody PersonDTO dto) throws Exception {
        if (dto != null){
            service.postPerson(converter.toEntity(dto));
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        throw new Exception(FAILED_TO_INSERT_NEW_PERSON);
    }

    @ApiOperation(value = "URL To Put Of Person")
    @PutMapping(path = "/person/put")
    public ResponseEntity<PersonDTO> putPerson(@Validated @RequestBody PersonDTO dto) throws Exception {
        PersonEntity entity = service.getPersonById(dto.getIdPerson());
        if (entity != null){
            service.postPerson(converter.toEntity(dto));
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        throw new Exception(FAILED_TO_UPDATE_PERSON);
    }

    @ApiOperation(value = "URL To Delete Of Person")
    @DeleteMapping(path = "/person/delete")
    public ResponseEntity<PersonDTO> deletePerson(@Validated @RequestParam Integer id) throws Exception {
        PersonEntity entity = service.getPersonById(id);
        if (entity != null){
            service.deletePersonById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw new Exception(FAILED_TO_DELETE_PERSON_BY_ID);
    }
}