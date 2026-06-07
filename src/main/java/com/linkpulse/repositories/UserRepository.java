package com.linkpulse.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.linkpulse.entities.User;
import com.linkpulse.repositories.base.BaseRepository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
