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
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "URL To Post Of Persons")
    @PostMapping(path = "/person/post")
    public ResponseEntity<PersonDTO> postPerson(@Validated @RequestBody PersonDTO dto) throws Exception {
        if (dto != null){
            service.postPerson(converter.toEntity(dto));
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "URL To Put Of Person")
    @PutMapping(path = "/person/put")
    public ResponseEntity<PersonDTO> putPerson(@Validated @RequestBody PersonDTO dto) throws Exception {
        PersonEntity entity = service.getPersonById(dto.getIdPerson());
        if (entity != null){
            service.postPerson(converter.toEntity(dto));
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "URL To Delete Of Person")
    @DeleteMapping(path = "/person/delete")
    public ResponseEntity<PersonDTO> deletePerson(@Validated @RequestParam Integer id) throws Exception {
        PersonEntity entity = service.getPersonById(id);
        if (entity != null){
            service.deletePersonById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}