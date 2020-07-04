package com.siit.spring.domain.model;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlbumDTO {

    private long id;

    private String title;

    private LocalDate releaseDate;

    private SingerDTO singer;
}
