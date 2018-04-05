package com.example.usersapi.repositories;

import com.example.usersapi.models.User;
import com.google.common.collect.Iterables;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        User firstUser = new User(
                "some_email1@email.com",
                "SomePassw0rd!"
        );

        User secondUser = new User(
                "some_email2@email.com",
                "SomePassw0rd!2"
        );

        entityManager.persist(firstUser);
        entityManager.persist(secondUser);
        entityManager.flush();
    }

    @Test
    public void findAll_returnsAllUsers() {
        Iterable<User> usersFromDb = userRepository.findAll();

        assertThat(Iterables.size(usersFromDb), is(2));
    }

    @Test
    public void findAll_returnsEmail() {
        Iterable<User> usersFromDb = userRepository.findAll();

        String secondUsersEmail = Iterables.get(usersFromDb, 1).getEmail();

        assertThat(secondUsersEmail, is("some_email2@email.com"));
    }


    @Test
    public void findAll_returnsPassword() {
        Iterable<User> usersFromDb = userRepository.findAll();

        String secondUsersPassword = Iterables.get(usersFromDb, 1).getPassword();

        assertThat(secondUsersPassword, is("SomePassw0rd!2"));
    }


}
