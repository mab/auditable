package de.saxsys.playground.auditable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;

import java.time.Instant;
import java.util.UUID;

public abstract class InsertableEntity extends IdentifiableEntity {
    @CreatedDate
    @JsonIgnore
    private Instant createdAt;

    @LastModifiedDate
    @JsonIgnore
    private Instant updatedAt;

    @Transient
    private boolean setIdCalled = false;

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setId(UUID id) {
        super.setId(id);
        LoggerFactory.getLogger(this.getClass().getName()).info("setId was called");

        setIdCalled = true;
    }


    @Override
    public boolean isNew() {

        boolean isNew = super.isNew() || createdAt == null;

        if (isNew && !setIdCalled ) {
            LoggerFactory.getLogger(this.getClass().getName()).info("isNew " + isNew + " changed because "
                                                                            + "isNewWasCalled");

            isNew = false;
        }
//        LoggerFactory.getLogger(this.getClass().getName()).info("isNew " + isNew, new Exception("isNew"));
        LoggerFactory.getLogger(this.getClass().getName()).info("isNew " + isNew);
        return isNew;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + "InsertableEntity{" + "createdAt=" + createdAt
                + " updateAt=" + updatedAt
                + '}';
    }
}
