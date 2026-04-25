package com.linkpulse.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="domains")
@Getter
@Setter
public class Domain extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private Organization organization;

    @Column(name="domain_name", nullable = false)
    private String domainName;

    @Column(name="is_verified")
    private Boolean isVerified;
}
