package com.example.app.security.repositories;

import com.example.app.security.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    boolean existsByUsername(String username);

    // Добавлен метод для поиска в БД по имени
    Optional<AppUser> findByUsername(String username);

}
