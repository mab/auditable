package de.saxsys.playground.auditable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("de.saxsys.playground.auditable.MyDocument")
public class MyDocument extends InsertableEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + "MyDocument{" + "name='" + name + "'" + " updateElement='" + updateElement
                + "' }";
    }

    private String updateElement = "";

    public String getUpdateElement() {
        return updateElement;
    }

    public void setUpdateElement(String updateElement) {
        this.updateElement = updateElement;
    }

}
