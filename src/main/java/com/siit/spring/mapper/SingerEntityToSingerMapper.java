package com.siit.spring.mapper;

import com.siit.spring.domain.entity.AlbumEntity;
import com.siit.spring.domain.entity.SingerEntity;
import com.siit.spring.domain.model.AlbumDTO;
import com.siit.spring.domain.model.SingerDTO;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SingerEntityToSingerMapper implements Converter<SingerEntity, SingerDTO> {

    private final AlbumEntityToAlbumMapper albumEntityToAlbumMapper;

    @Override
    public SingerDTO convert(SingerEntity source) {
        return SingerDTO.builder()
                        .id(source.getId())
                        .firstName(source.getFirstName())
                        .lastName(source.getLastName())
                        .birthDate(source.getBirthDate())
                        .albums(mapAlbumsList(source.getAlbums()))
                        //ca sa te asiguri ca sunt deja retrase din DB in query-ul initial,
                        //folosesti join fetch si in acest fel JPA ca genera un singur query cu JOIN
                        //aducand toate datele necesare
                        .build();
    }

    private List<AlbumDTO> mapAlbumsList(List<AlbumEntity> albums) {
        if (albums == null) {
            return Collections.emptyList();
        }
        return albums.stream()
                     .map(albumEntityToAlbumMapper::convert)
                     .collect(Collectors.toList());
    }
}
