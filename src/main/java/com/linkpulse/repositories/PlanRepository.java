package com.linkpulse.repositories;

import java.util.Optional;

import org.hibernate.query.Page;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.linkpulse.entities.Link;
import com.linkpulse.entities.Plan;
import com.linkpulse.repositories.base.BaseRepository;

@Repository
public interface PlanRepository extends BaseRepository<Plan, Long> {
    Optional<Plan> findByName(String name);

    Pageable pageable = PageRequest.of(0, 10, Sort.by("createdAt").descending());

    Page<Link> links = LinkRepository.findByUserId(userId, pageable);
}
