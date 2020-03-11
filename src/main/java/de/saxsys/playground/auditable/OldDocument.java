package de.saxsys.playground.auditable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("de.saxsys.playground.auditable.MyDocument")
public class OldDocument extends IdentifiableEntity {
    private String updateElement = "";

    public String getUpdateElement() {
        return updateElement;
    }

    public void setUpdateElement(String updateElement) {
        this.updateElement = updateElement;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +"OldDocument{" + "updateElement='" + updateElement + '\'' + '}';
    }
}
