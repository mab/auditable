package de.saxsys.playground.auditable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;

import java.util.UUID;

public abstract class IdentifiableEntity implements Persistable<UUID> {
    @Id
    private UUID id;

    @JsonIgnore
    public boolean isNew() {
        return getId() == null;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
