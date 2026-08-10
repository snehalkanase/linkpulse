package com.linkpulse.repositories;

import com.linkpulse.entities.Subscription;
import com.linkpulse.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubscriptionRepository extends BaseRepository<Subscription, UUID> {
    Optional<Subscription> findByOrganizationId(UUID organizationId);
}
