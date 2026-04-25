package com.linkpulse.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="api_keys",
uniqueConstraints = @UniqueConstraint(columnNames = "api_key")
)
@Getter
@Setter
public class ApiKey extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @Column(name="api_key", nullable = false)
    private String apiKey;

    private String name;

    @Column(name="is_active")
    private boolean isActive;
}
