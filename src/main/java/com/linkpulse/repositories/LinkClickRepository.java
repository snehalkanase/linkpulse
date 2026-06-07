package com.linkpulse.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.linkpulse.entities.LinkClick;
import com.linkpulse.repositories.base.BaseRepository;

@Repository
public interface LinkClickRepository extends BaseRepository<LinkClick, Long> {

    @Query("""
                SELECT COUNT(c)
                FROM LinkClick c
                WHERE c.link.id = :linkId
            """)
    Long countClicksByLinkId(Long linkId);

    @Query("""
                SELECT c.country, COUNT(c)
                FROM LinkClick c
                WHERE c.link.id = :linkId
                GROUP BY c.country
            """)
    List<Object[]> getClicksByCountry(Long linkId);

    @Query("""
                SELECT DATE(c.createdAt), COUNT(c)
                FROM LinkClick c
                WHERE c.link.id = :linkId
                GROUP BY DATE(c.createdAt)
                ORDER BY DATE(c.createdAt)
            """)
    List<Object[]> getDailyClicks(Long linkId);

}
