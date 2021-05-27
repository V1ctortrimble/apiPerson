package com.api.brq.service;

import com.api.brq.data.entity.PersonEntity;
import com.api.brq.data.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    /**
     * Repository
     */
    private final PersonRepository repository;

    public List<PersonEntity> getAllPersons() { return repository.findAll(); }

    public PersonEntity getPersonById(Integer id){ return repository.findByIdPerson(id); }

    public void postPerson(PersonEntity entity) { repository.save(entity); }

    public void deletePersonById(Integer id) { repository.deleteById(id); }
}