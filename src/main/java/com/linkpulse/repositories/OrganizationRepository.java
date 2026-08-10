package com.linkpulse.repositories;

import com.linkpulse.entities.Organization;
import com.linkpulse.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrganizationRepository extends BaseRepository<Organization, UUID> {
}
