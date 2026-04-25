package com.linkpulse.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name="links",
        uniqueConstraints = @UniqueConstraint(columnNames = "short_url")
)
@Getter
@Setter
public class Link extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="domain_id")
    private Domain domain;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "original_url", nullable = false)
    private String originalUrl;

    @Column(name = "short_url", nullable = false)
    private String shortUrl;

    private String title;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "expires_at")
    private Instant expiresAt;

    @OneToMany(mappedBy = "link", fetch = FetchType.LAZY)
    private List<LinkClick> clicks;
}
