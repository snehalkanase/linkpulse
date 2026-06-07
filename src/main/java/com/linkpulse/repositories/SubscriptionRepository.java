package com.linkpulse.repositories;

import org.springframework.stereotype.Repository;

import com.linkpulse.entities.Subscription;
import com.linkpulse.repositories.base.BaseRepository;

@Repository
public interface SubscriptionRepository extends BaseRepository<Subscription, Long> {
}
