package com.siit.spring.domain.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingerFilter {
    String name;

    @DateTimeFormat(iso = DATE)
    LocalDate birthDate;
}
