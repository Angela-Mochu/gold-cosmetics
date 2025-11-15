package com.goldcosmetics.repository;

import com.goldcosmetics.model.User;
import com.goldcosmetics.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    List<User> findByRole(Role role);

    List<User> findByRoleAndShopLocation(Role role, String shopLocation);

    List<User> findByIsActiveTrue();

    List<User> findByIsActiveFalse();

    Long countByRole(Role role);
}
