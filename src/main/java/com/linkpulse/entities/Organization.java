package com.linkpulse.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "organizations")
@Getter
@Setter
public class Organization extends BaseEntity{
    @Column(nullable = false)
    private String name;

    @Column(name = "plan_type")
    private String planType;

    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
    private List<User> users;

    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
    private List<ApiKey> apiKeys;

    @OneToMany(mappedBy = "organization" , fetch = FetchType.LAZY)
    private List<Domain> domains;

    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
    private List<Link> links;

    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
    private Subscription subscription;
}
