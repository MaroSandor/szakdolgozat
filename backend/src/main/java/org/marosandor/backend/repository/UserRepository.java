package org.marosandor.backend.repository;

import org.marosandor.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    boolean existByEmail(String email);
    boolean existByUsername(String username);
}
