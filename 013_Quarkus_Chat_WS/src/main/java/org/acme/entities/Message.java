package org.acme.entities;

import java.time.Instant;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Message extends PanacheEntity {

    public Instant timestamp;

    @Column(columnDefinition = "text")
    public String text;

    public String userId;
}
