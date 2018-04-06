package com.example.features;

import com.example.models.Organization;
import com.example.repostitories.OrganizationRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;


import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class OrganizationsApiFeatureTest {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Before
    public void setUp() {
        organizationRepository.deleteAll();
    }

    @After
    public void tearDown() {
        organizationRepository.deleteAll();
    }

    @Test
    public void shouldAllowFullCrudForAOrganization() throws Exception {
        Organization firstOrganization = new Organization (

        );

        Organization secondOrganization = new Organization (

        );

        Stream.of(firstOrganization, secondOrganization)
                .forEach(organization -> {
                    organizationRepository.save(organization);
                });

        when()
                .get("http://localhost:8080/organizations/")
                .then()
                .statusCode(is(200))
                .and().body(containsString("someone@email.com"))
                .and().body(containsString("Abcd123!"));
    }
}