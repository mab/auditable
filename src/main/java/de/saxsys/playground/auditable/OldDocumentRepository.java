package de.saxsys.playground.auditable;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OldDocumentRepository extends MongoRepository<OldDocument, String> {
}
