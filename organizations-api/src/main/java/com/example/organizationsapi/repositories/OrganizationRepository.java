package com.example.organizationsapi.repositories;

import com.example.organizationsapi.models.Organization;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public interface OrganizationRepository extends CrudRepository<Organization, Long> {

}