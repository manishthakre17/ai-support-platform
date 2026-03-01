package com.aisupport.auth_service.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.aisupport.auth_service.entity.User;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveAndFindUserByEmail() {

        User user = User.builder()
                .name("Manish")
                .email("manish@test.com")
                .password("password123")
                .build();

        userRepository.save(user);

        Optional<User> foundUser = userRepository.findByEmail("manish@test.com");

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getName()).isEqualTo("Manish");
    }
}