package com.example.app.security.repositories;

import com.example.app.security.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    boolean existsByUsername(String username);
}
