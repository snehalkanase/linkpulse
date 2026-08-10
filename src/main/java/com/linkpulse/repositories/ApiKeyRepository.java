package com.linkpulse.repositories;

import com.linkpulse.entities.ApiKey;
import com.linkpulse.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ApiKeyRepository extends BaseRepository<ApiKey, UUID> {
    Optional<ApiKey> findByApiKey(String apiKey);
}
