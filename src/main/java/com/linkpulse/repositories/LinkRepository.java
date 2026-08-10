package com.linkpulse.repositories;

import com.linkpulse.entities.Link;
import com.linkpulse.repositories.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LinkRepository extends BaseRepository<Link, UUID> {
    Optional<Link> findByShortUrl(String shortUrl);

    boolean existsByShortUrl(String shortUrl);

    Page<Link> findByCreatedById(UUID userId, Pageable pageable);

    Page<Link> findByOrganizationId(UUID organizationId, Pageable pageable);

    @Query("SELECT l FROM Link l WHERE l.organization.id = :orgId AND (:search IS NULL OR LOWER(l.originalUrl) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(l.title) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(l.shortUrl) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Link> searchOrganizationLinks(@Param("orgId") UUID orgId, @Param("search") String search, Pageable pageable);
}
