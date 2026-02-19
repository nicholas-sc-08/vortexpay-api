package com.pay.vortexpay.repository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.pay.vortexpay.entities.User;
import com.pay.vortexpay.repositories.UserRepository;
import com.pay.vortexpay.shared.UserRole;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    private User createUser(User data) {
        entityManager.persist(data);
        return data;
    }

    @Test
    @DisplayName("Should get user successfully by Email.")
    void findByEmailCaseOne() {
        User user = new User(null, "Random@gmail.com", "1234567", UserRole.USER, null);
        createUser(user);

        Optional<User> result = userRepository.findUserByEmail(user.getEmail());
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not find the email from DB")
    void findByEmailCaseTwo() {
        String email = "Random@gmail.com";

        Optional<User> result = userRepository.findUserByEmail(email);
        assertThat(result.isEmpty()).isTrue();
    }
}
