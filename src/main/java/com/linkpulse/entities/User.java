package com.linkpulse.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="users",
uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@Getter
@Setter
public class User extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private Organization organization;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(name="password_hash", nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String role;

    @Column(name="is_verified")
    private boolean isVerified;

    @OneToMany(mappedBy = "createdBy", fetch = FetchType.LAZY)
    private List<Link> links;
}
