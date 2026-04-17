package com.ali.store.repositories;

import com.ali.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
