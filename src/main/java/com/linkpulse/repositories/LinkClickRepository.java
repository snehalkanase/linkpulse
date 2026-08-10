package com.linkpulse.repositories;

import com.linkpulse.entities.LinkClick;
import com.linkpulse.repositories.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LinkClickRepository extends BaseRepository<LinkClick, UUID> {
    long countByLinkId(UUID linkId);

    @Query("SELECT COUNT(DISTINCT lc.ipAddress) FROM LinkClick lc WHERE lc.link.id = :linkId")
    long countUniqueVisitorsByLinkId(@Param("linkId") UUID linkId);

    @Query("SELECT lc.country AS label, COUNT(lc) AS count FROM LinkClick lc WHERE lc.link.id = :linkId GROUP BY lc.country ORDER BY COUNT(lc) DESC")
    List<Object[]> getCountryStatsRaw(@Param("linkId") UUID linkId);

    @Query("SELECT lc.deviceType AS label, COUNT(lc) AS count FROM LinkClick lc WHERE lc.link.id = :linkId GROUP BY lc.deviceType ORDER BY COUNT(lc) DESC")
    List<Object[]> getDeviceStatsRaw(@Param("linkId") UUID linkId);

    @Query("SELECT lc.browser AS label, COUNT(lc) AS count FROM LinkClick lc WHERE lc.link.id = :linkId GROUP BY lc.browser ORDER BY COUNT(lc) DESC")
    List<Object[]> getBrowserStatsRaw(@Param("linkId") UUID linkId);

    @Query("SELECT lc.referrer AS label, COUNT(lc) AS count FROM LinkClick lc WHERE lc.link.id = :linkId GROUP BY lc.referrer ORDER BY COUNT(lc) DESC")
    List<Object[]> getReferrerStatsRaw(@Param("linkId") UUID linkId);
}
