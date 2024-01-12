package com.example.carservice.data.repository;

import com.example.carservice.data.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RoleRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    void findByAuthority() {
        Role role = new Role();
        role.setAuthority("example");
        testEntityManager.persistAndFlush(role);

        assertThat(roleRepository.findByAuthority("example").getAuthority()).isEqualTo("example");
    }


}
