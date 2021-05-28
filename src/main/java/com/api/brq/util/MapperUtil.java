package com.api.brq.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * component to map entities and DTO
 */
@Component
public class MapperUtil {

    private final ModelMapper modelMapper;

    public MapperUtil(){
        this.modelMapper = new ModelMapper();
    }

    public ModelMapper use(){
        return this.modelMapper;
    }
}