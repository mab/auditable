package de.saxsys.playground.auditable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MyDocument extends InsertableEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
