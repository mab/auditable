package de.saxsys.playground.auditable;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@SpringBootTest
class MyDocumentRepositoryTest {

    @Autowired
    private MyDocumentRepository repository;

    @Test
    void shouldCreateMyDocumentWithCreatedAtTime() {
        MyDocument document = new MyDocument();
        document.setName("My name");

        MyDocument persistedDocument = repository.save(document);
        assertThat(persistedDocument.getName()).isEqualTo("My name");
        assertThat(persistedDocument.getCreatedAt()).as("createdAt").isCloseTo(Instant.now(), within(1, ChronoUnit.SECONDS));
    }

}
