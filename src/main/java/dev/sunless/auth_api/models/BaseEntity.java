package dev.sunless.auth_api.models;


import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.*;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    private LocalDateTime createdDate;
    private LocalDateTime lastModified;
    private LocalDateTime deletedAt;

    public void softDelete() {
        this.deletedAt = now();
    }

    public boolean isSoftDeleted() {
        return deletedAt != null;
    }


    @PrePersist
    protected void onCreate() {
        this.createdDate = now();
        this.lastModified = createdDate;
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastModified = now();
    }
}
