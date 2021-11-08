package br.com.blueacademy.bluebank.entities;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    private UUID id;
    private Date createdAt;
    private Date updatedAt;
    private boolean active;

    public AbstractEntity() {
        id = UUID.randomUUID();
        var now = new Date();
        createdAt = now;
        updatedAt = now;
        active = true;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
