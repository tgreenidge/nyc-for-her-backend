package com.example.organizationsapi.controllers;


import com.example.organizationspi.models.Organization;
import com.example.organizationspi.repositories.OrganizationRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
public class OrganizationsController {

    @Autowired
    private OrganizationRepository organizationRepository;

    @GetMapping("/")
    public Iterable<Organization> findAllOrganizations() {
        return organizationRepository.findAll();
    }

    @GetMapping("/{organizationId}")
    public Organization findOrganizationById(@PathVariable Long organizationId) throws NotFoundException {

        Organization foundOrganization = organizationRepository.findOne(organizationId);

        if (foundOrganization == null) {
            throw new NotFoundException("Organization with ID of " + organizationId + " was not found!");
        }


        return foundOrganization;
    }

    @DeleteMapping("/{organizationId}")
    public HttpStatus deleteOrganizationById(@PathVariable Long organizationId) throws EmptyResultDataAccessException {
        organizationRepository.delete(organizationId);
        return HttpStatus.OK;
    }

    @PostMapping("/")
    public Organization createNewOrganization(@RequestBody Organization newOrganization) {
        return organizationRepository.save(newOrganization);
    }

//    @PatchMapping("/{organizationId}")
//    public Organization updateOrganizationById(@PathVariable Long organizationId, @RequestBody Organization organizationRequest) throws NotFoundException {
//        Organization organizationFromDb = organizationRepository.findOne(organizationId);
//
//        if (organizationFromDb == null) {
//            throw new NotFoundException("Organization with ID of " + organizationId + " was not found!");
//        }
//
//        organizationFromDb.setPassword(organizationRequest.getPassword());
//
//        return organizationRepository.save(organizationFromDb);
//    }

    @ExceptionHandler
    void handleOrganizationNotFound(
            NotFoundException exception,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler
    void handleDeleteNotFoundException(
            EmptyResultDataAccessException exception,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.NOT_FOUND.value());
    }
}
