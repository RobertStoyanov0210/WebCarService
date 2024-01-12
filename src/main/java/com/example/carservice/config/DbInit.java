package com.example.carservice.config;

import com.example.carservice.data.entity.Role;
import com.example.carservice.data.entity.User;
import com.example.carservice.data.repository.RoleRepository;
import com.example.carservice.data.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Set;

@Component
@AllArgsConstructor
public class DbInit implements
        ApplicationListener<ContextRefreshedEvent> {
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Role admin = new Role();
        if (roleRepository.findByAuthority("ADMIN") == null) {
            admin.setId(1);
            admin.setAuthority("ADMIN");
            roleRepository.save(admin);
        }
        if (roleRepository.findByAuthority("EMPLOYEE") == null) {
            Role employee = new Role();
            employee.setId(2);
            employee.setAuthority("EMPLOYEE");
            roleRepository.save(employee);
        }
        if (roleRepository.findByAuthority("CUSTOMER") == null) {
            Role customer = new Role();
            customer.setId(3);
            customer.setAuthority("CUSTOMER");
            roleRepository.save(customer);
        }

        if (userRepository.findByUsername("admin") == null) {
            User adminUser = new User();
            adminUser.setFirstName("Robert");
            adminUser.setLastName("Stoyanov");
            adminUser.setUsername("admin");
            adminUser.setPassword(new BCryptPasswordEncoder().encode("admin"));
            adminUser.setAuthorities(Set.of(admin));
            userRepository.save(adminUser);
        }

    }
}
