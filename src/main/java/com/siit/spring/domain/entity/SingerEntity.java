package com.siit.spring.domain.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "singer")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingerEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)//folositi identity pt auto_increment
    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    @OneToMany(mappedBy = "singer", fetch = FetchType.LAZY)
    private List<AlbumEntity> albums;

}
