package com.siit.spring.service;

import com.siit.spring.domain.entity.AlbumEntity;
import com.siit.spring.domain.entity.SingerEntity;
import com.siit.spring.domain.model.AlbumDTO;
import com.siit.spring.exception.SingerNotFoundException;
import com.siit.spring.mapper.AlbumEntityToAlbumMapper;
import com.siit.spring.mapper.AlbumToAlbumEntityMapper;
import com.siit.spring.repository.AlbumRepository;
import com.siit.spring.repository.SingerRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AlbumService {

    private final AlbumRepository repository;

    private final SingerRepository singerRepository;

    private final AlbumToAlbumEntityMapper albumToAlbumEntityMapper;

    private final AlbumEntityToAlbumMapper albumEntityToAlbumMapper;

    public AlbumDTO create(AlbumDTO album, long singerId) {
        Optional<SingerEntity> optionalSingerEntity = singerRepository.findById(singerId);
        SingerEntity singerEntity = optionalSingerEntity.orElseThrow(() -> new SingerNotFoundException("Singer with id " + singerId + " not found"));

        AlbumEntity albumEntity = albumToAlbumEntityMapper.convert(album);
        albumEntity.setSinger(singerEntity);
        AlbumEntity savedAlbumEntity = repository.save(albumEntity);

        return albumEntityToAlbumMapper.convert(savedAlbumEntity);
    }
}
