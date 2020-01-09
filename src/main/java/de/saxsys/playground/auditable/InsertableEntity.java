package de.saxsys.playground.auditable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

public abstract class InsertableEntity extends IdentifiableEntity {
    @CreatedDate
    @JsonIgnore
    private Instant createdAt;

    public Instant getCreatedAt() {
        return createdAt;
    }
}
