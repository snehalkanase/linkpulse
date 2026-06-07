package com.linkpulse.repositories;

import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.linkpulse.dto.LinkSummaryDTO;
import com.linkpulse.entities.Link;
import com.linkpulse.repositories.base.BaseRepository;

@Repository
public interface LinkRepository extends BaseRepository<Link, Long> {
    Optional<Link> findByShortCode(String shortCode);

    boolean existsByShortCode(String shortCode);

    Page<Link> findByUserId(Long userId, Pageable pageable);

    @Query("""
                SELECT l.shortCode AS shortCode,
                       l.originalUrl AS originalUrl,
                       l.clickCount AS clickCount
                FROM Link l
                WHERE l.user.id = :userId
            """)
    Page<LinkSummaryDTO> getUserLinksSummary(Long userId, Pageable pageable);

    @Query("""
                SELECT l FROM Link l
                WHERE l.user.id = :userId
                AND (:search IS NULL OR l.originalUrl LIKE %:search%)
            """)
    Page<Link> searchUserLinks(Long userId, String search, Pageable pageable);

    Page<Link> findByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);
}
