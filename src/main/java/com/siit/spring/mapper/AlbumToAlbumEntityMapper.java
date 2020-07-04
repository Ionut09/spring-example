package com.siit.spring.mapper;

import com.siit.spring.domain.entity.AlbumEntity;
import com.siit.spring.domain.model.AlbumDTO;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AlbumToAlbumEntityMapper {

    public AlbumEntity convert(AlbumDTO album) {
        return null;
    }
}
