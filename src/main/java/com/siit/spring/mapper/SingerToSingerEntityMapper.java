package com.siit.spring.mapper;

import com.siit.spring.domain.entity.SingerEntity;
import com.siit.spring.domain.model.SingerDTO;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SingerToSingerEntityMapper implements Converter<SingerDTO, SingerEntity> {

    @Override
    public SingerEntity convert(SingerDTO source) {
        return SingerEntity.builder()
                           .firstName(source.getFirstName())
                           .lastName(source.getLastName())
                           .birthDate(source.getBirthDate())
                           .build();
    }
}
