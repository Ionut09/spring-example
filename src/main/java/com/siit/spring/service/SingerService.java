package com.siit.spring.service;

import com.siit.spring.domain.entity.SingerEntity;
import com.siit.spring.domain.model.SingerDTO;
import com.siit.spring.exception.SingerNotFoundException;
import com.siit.spring.mapper.SingerEntityToSingerMapper;
import com.siit.spring.mapper.SingerToSingerEntityMapper;
import com.siit.spring.repository.SingerRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Validated
public class SingerService {

    private final SingerRepository repository;

    private final SingerEntityToSingerMapper singerEntityToSingerMapper;

    private final SingerToSingerEntityMapper singerToSingerEntityMapper;

    public SingerDTO create(@Valid SingerDTO singer) {
        SingerEntity singerEntity = singerToSingerEntityMapper.convert(singer);
        SingerEntity savedEntity = repository.save(singerEntity);
        return singerEntityToSingerMapper.convert(savedEntity);
    }

    public SingerDTO findById(long singerId) {
        return repository.findById(singerId)
//                       .map(mapper::convert)
                         .map((SingerEntity singerEntity) -> singerEntityToSingerMapper.convert(singerEntity))
                         .orElseThrow(() -> new SingerNotFoundException("The singer with id provided cannot be found"));
    }

    public List<SingerDTO> getAll() {
        return repository.getAll()
                         .stream()
                         .map(singerEntityToSingerMapper::convert)
                         .collect(Collectors.toList());
    }

    public SingerDTO update(SingerDTO singer) {
        SingerEntity singerEntity = singerToSingerEntityMapper.convert(singer);
        singerEntity.setId(singer.getId());
        SingerEntity savedEntity = repository.save(singerEntity);

        return singerEntityToSingerMapper.convert(savedEntity);
    }

    @Transactional //
    public void updateTransactional(SingerDTO singer) {
        SingerEntity existingEntity = repository.findById(singer.getId())
                                                .orElseThrow(() -> new SingerNotFoundException("The singer with id provided cannot be found"));

        updateFields(existingEntity, singer);
    }

    private void updateFields(SingerEntity existingEntity, SingerDTO singer) {
        existingEntity.setBirthDate(singer.getBirthDate());
        existingEntity.setFirstName(singer.getFirstName());
        existingEntity.setLastName(singer.getLastName());
    }
}
