package com.siit.spring.domain.model;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SingerDTO {

    private long id;
//
//    @Positive
//    private int age;

    @Size(min = 2, max = 50)
    private String firstName;

    @Size(min = 2, max = 50)
    @NotEmpty
    @NotNull
    private String lastName;

    @Past
    private LocalDate birthDate;
//
//    @Future
//    private LocalDate expiryDate;

    @NotEmpty
    private List<AlbumDTO> albums;
}
