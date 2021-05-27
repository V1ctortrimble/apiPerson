package com.api.brq.converter;

import com.api.brq.data.entity.PersonEntity;
import com.api.brq.dto.PersonDTO;
import com.api.brq.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonConverter extends DTOEntityConverter<PersonDTO, PersonEntity>{

    /**
     * Component
     */
    private final MapperUtil mapperUtil;

    @Override
    protected PersonDTO toDTOImp(PersonEntity entity){
        return mapperUtil.use().map(entity, PersonDTO.class);
    }
    @Override
    protected PersonEntity toEntityImp(PersonDTO dto){
        return mapperUtil.use().map(dto, PersonEntity.class);
    }
}