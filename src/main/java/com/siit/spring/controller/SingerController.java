package com.siit.spring.controller;

import com.ApplicationProperties;
import com.siit.spring.domain.model.SingerDTO;
import com.siit.spring.domain.model.SingerFilter;
import com.siit.spring.exception.SingerNotFoundException;
import com.siit.spring.service.SingerService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/singers")
@AllArgsConstructor
public class SingerController {

    //constructor injection
    private final SingerService service;

    private final ApplicationProperties properties;

    @Value("com.siit.studentNames")
    private List<String> studentNames;

    @GetMapping("/{id}") //    /singers/10
    @ResponseStatus(HttpStatus.OK)
    public SingerDTO getOneSinger(@PathVariable("id") long id) {
        return service.findById(id);
    }

    @GetMapping //    /singers
    @ResponseStatus(HttpStatus.OK)
    public List<SingerDTO> getAllSingerrs() {
        System.out.println("The number of students: " + properties.getNumberOfStudents());
        System.out.println("The names of students: " + studentNames);
        return service.getAll();
    }

    @GetMapping("/filter")//("?name={name}&birthDate={birthDate}") //    /singers
    public List<SingerDTO> getAllSingerrsByName(SingerFilter filter) {
        System.out.println("filter = " + filter);
        return service.getAll()
                      .stream().filter(singerDTO -> singerDTO.getFirstName().equals(filter.getName()))
                      .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SingerDTO create(@RequestBody SingerDTO singer) {
        return service.create(singer);
    }
    //{id:12,
    // firstName: "sjansjan",}

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") long id, @RequestBody SingerDTO singer) {
        singer.setId(id);
        service.updateTransactional(singer);
    }

    @ExceptionHandler(SingerNotFoundException.class)
    public void notFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }
}
