package com.siit.spring.controller;

import com.siit.spring.domain.model.AlbumDTO;
import com.siit.spring.service.AlbumService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/singers/{id}/albums")
@AllArgsConstructor
public class AlbumController {

    private final AlbumService service;

//    @Secured("ROLE_ADMIN")
    @PreAuthorize("#authorisationService.checkIFThisUserHasRightsForThisMethod()")
    @PostMapping
    public AlbumDTO create(@PathVariable("id") Long singerId, @RequestBody AlbumDTO album) {
        return service.create(album, singerId);
    }
}
