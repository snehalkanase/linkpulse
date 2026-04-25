package com.linkpulse.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name="link_clicks")
@Getter
@Setter
public class LinkClick extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="link_id")
    private Link link;

    @Column(name="ip_address")
    private String ipAddress;

    private String country;

    private String city;

    @Column(name="device_type")
    private String deviceType;

    private String browser;

    private String referrer;

    @Column(name="clicked_at")
    private Instant clickedAt;
}
