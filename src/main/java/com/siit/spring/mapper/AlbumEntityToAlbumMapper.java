package com.siit.spring.mapper;

import com.siit.spring.domain.entity.AlbumEntity;
import com.siit.spring.domain.model.AlbumDTO;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AlbumEntityToAlbumMapper implements Converter<AlbumEntity, AlbumDTO> {

    @Override
    public AlbumDTO convert(AlbumEntity source) {
        return AlbumDTO.builder()
                       .id(source.getId())
                       .releaseDate(source.getReleaseDate())
                       .build();
    }
}
