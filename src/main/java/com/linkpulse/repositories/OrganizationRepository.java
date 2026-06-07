package com.linkpulse.repositories;

import org.springframework.stereotype.Repository;

import com.linkpulse.entities.Organization;
import com.linkpulse.repositories.base.BaseRepository;

@Repository
public interface OrganizationRepository extends BaseRepository<Organization, Long> {
}
