package com.example.usersapi.controllers;


import com.example.usersapi.models.User;
import com.example.usersapi.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;


import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(UsersController.class)
public class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private User newUser;

    //private User updatedSecondUser;


    @Autowired
    private ObjectMapper jsonObjectMapper;


    @MockBean
    private UserRepository mockUserRepository;

    @Before
    public void setUp() {
        User firstUser = new User(
                "someone@email.com",
                "Abcd123!"
        );

        User secondUser = new User(
                "someone_else@email.com",
                "Abcd123@"
        );

        newUser = new User(
                "someone_new@email.com",
                "Abcd1234@"
        );
        given(mockUserRepository.save(newUser)).willReturn(newUser);

        //unsure how to update this at this point in time
//        updatedSecondUser = new User(
//                "UpdatedPassw0rd!"
//        );
//
//        given(mockUserRepository.save(updatedSecondUser)).willReturn(updatedSecondUser);

        Iterable<User> mockUsers =
                Stream.of(firstUser, secondUser).collect(Collectors.toList());

        given(mockUserRepository.findAll()).willReturn(mockUsers);
        given(mockUserRepository.findOne(1L)).willReturn(firstUser);
        given(mockUserRepository.findOne(4L)).willReturn(null);
        doAnswer(invocation -> {
            throw new EmptyResultDataAccessException("ERROR MESSAGE FROM MOCK!!!", 1234);
        }).when(mockUserRepository).delete(4L);


    }

    @Test
    public void findAllUsers_success_returnsStatusOK() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllUsers_success_returnAllUsersAsJSON() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void findAllUsers_success_returnEmailForEachUser() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].email", is("someone@email.com")));
    }

    @Test
    public void findAllUsers_success_returnPasswordForEachUser() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].password", is("Abcd123!")));
    }


    @Test
    public void findUserById_success_returnsStatusOK() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void findUserById_success_returnEmail() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(jsonPath("$.email", is("someone@email.com")));
    }

    @Test
    public void findUserById_success_returnPassword() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(jsonPath("$.password", is("Abcd123!")));
    }


    @Test
    public void findUserById_failure_userNotFoundReturns404() throws Exception {

        this.mockMvc
                .perform(get("/4"))
                .andExpect(status().reason(containsString("User with ID of 4 was not found!")));
    }

    @Test
    public void deleteUserById_success_returnsStatusOk() throws Exception {

        this.mockMvc
                .perform(delete("/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUserById_success_deletesViaRepository() throws Exception {

        this.mockMvc.perform(delete("/1"));

        verify(mockUserRepository, times(1)).delete(1L);
    }

    @Test
    public void deleteUserById_failure_userNotFoundReturns404() throws Exception {

        this.mockMvc
                .perform(delete("/4"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createUser_success_returnsStatusOk() throws Exception {

        this.mockMvc
                .perform(
                        post("/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newUser))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void createUser_success_returnsEmail() throws Exception {

        this.mockMvc
                .perform(
                        post("/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newUser))
                )
                .andExpect(jsonPath("$.email", is("someone_new@email.com")));
    }

    @Test
    public void createUser_success_returnsPassword() throws Exception {

        this.mockMvc
                .perform(
                        post("/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newUser))
                )
                .andExpect(jsonPath("$.password", is("Abcd1234@")));
    }

//
//    @Test
//    public void updateUserById_success_returnsStatusOk() throws Exception {
//
//        this.mockMvc
//                .perform(
//                        patch("/1")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(jsonObjectMapper.writeValueAsString(updatedSecondUser))
//                )
//                .andExpect(status().isOk());
//    }


//    @Test
//    public void updateUserById_success_returnsUpdatedPassword() throws Exception {
//
//        this.mockMvc
//                .perform(
//                        patch("/1")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(jsonObjectMapper.writeValueAsString(updatedSecondUser))
//                )
//                .andExpect(jsonPath("$.password", is("UpdatedPassw0rd!")));
//    }


//    @Test
//    public void updateUserById_failure_userNotFoundReturns404() throws Exception {
//
//        this.mockMvc
//                .perform(
//                        patch("/4")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(jsonObjectMapper.writeValueAsString(updatedSecondUser))
//                )
//                .andExpect(status().isNotFound());
//    }

//    @Test
//    public void updateUserById_failure_userNotFoundReturnsNotFoundErrorMessage() throws Exception {
//
//        this.mockMvc
//                .perform(
//                        patch("/4")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(jsonObjectMapper.writeValueAsString(updatedSecondUser))
//                )
//                .andExpect(status().reason(containsString("User with ID of 4 was not found!")));
//    }




}

