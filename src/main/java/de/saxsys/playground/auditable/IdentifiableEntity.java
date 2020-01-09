package de.saxsys.playground.auditable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;

public abstract class IdentifiableEntity implements Persistable<String> {
    @Id
    private String id;

    @Override
    @JsonIgnore
    public boolean isNew() {
        return getId() == null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
