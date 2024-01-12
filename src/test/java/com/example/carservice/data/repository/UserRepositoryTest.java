package com.example.carservice.data.repository;

import com.example.carservice.data.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private UserRepository userRepository;

    @Test
    void findByUsername() {
        User user = new User();
        user.setUsername("name");
        user.setFirstName("fn");
        user.setPassword("1234");
        testEntityManager.persistAndFlush(user);
        assertThat(userRepository.findByUsername("name").getUsername()).isEqualTo("name");
    }

}
