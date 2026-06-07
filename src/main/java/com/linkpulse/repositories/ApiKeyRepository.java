package com.linkpulse.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.linkpulse.entities.ApiKey;
import com.linkpulse.repositories.base.BaseRepository;

@Repository
public interface ApiKeyRepository extends BaseRepository<ApiKey, Long> {
    Optional<ApiKey> findByKey(String key);
}
