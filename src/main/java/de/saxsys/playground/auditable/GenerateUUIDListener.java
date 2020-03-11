package de.saxsys.playground.auditable;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import java.util.UUID;

public class GenerateUUIDListener extends AbstractMongoEventListener<IdentifiableEntity> {
    @Override
    public void onBeforeConvert(BeforeConvertEvent<IdentifiableEntity> event) {
        IdentifiableEntity entity = event.getSource();
        System.out.println("onBeforeConvert " + entity);
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID());
        }
    }
}
