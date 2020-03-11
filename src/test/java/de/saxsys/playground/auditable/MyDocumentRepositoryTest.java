package de.saxsys.playground.auditable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@SpringBootTest
class MyDocumentRepositoryTest {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private MyDocumentRepository repository;
    @Autowired
    private OldDocumentRepository oldRepository;

    private UUID doc1Id;
    private UUID doc2Id;

    @BeforeEach
    void beforeEach() {
        OldDocument oldDocument = new OldDocument();
        oldDocument.setUpdateElement("doc 1");
        OldDocument save = oldRepository.save(oldDocument);

        doc1Id = save.getId();

        logger.info("Save " + save);

        oldDocument = new OldDocument();
        oldDocument.setUpdateElement("doc 2");
        OldDocument save2 = oldRepository.save(oldDocument);

        logger.info("Save " + save2);
        doc2Id = save2.getId();
/*

        String jsonString = "{\"_id\":\"07bf0097-0ec8-41b9-89e1-0dd89dbebad6\",\"_class\":\"de.saxsys.playground.auditable"
                + ".MyDocument\",\"updateElement\":{\"\":\"Save 11\"}}";
        String jsonString2 = "{\"_id\":\"17bf0097-0ec8-41b9-79e1-0dd89dbebad6\",\"_class\":\"de.saxsys.playground"
                + ".auditable.MyDocument\",\"updateElement\":{\"\":\"Save 21\"}}";

        Document doc = Document.parse(jsonString);
        mongoTemplate.insert(doc, "MyDocument");
        Document doc2 = Document.parse(jsonString2);
        Document myDocument2 = mongoTemplate.insert(doc2, "MyDocument");

        logger.info("inserted all "+ myDocument2);
*/

    }

    @Test
    void shouldCreateMyDocumentWithCreatedAtTime() {
        MyDocument document = new MyDocument();
        document.setName("My name");

        MyDocument persistedDocument = repository.save(document);

        System.out.println("Persist Document " + persistedDocument);
        assertThat(persistedDocument.getName()).isEqualTo("My name");
        assertThat(persistedDocument.getCreatedAt()).as("createdAt")
                .isCloseTo(Instant.now(), within(1, ChronoUnit.SECONDS));
    }

    @Test
    void findTestFromExisting() {

        List<MyDocument> all = repository.findAll();

        logger.info("Find size " + all.size());

        for (MyDocument document : all) {
            logger.info("Document " + document);
        }
        assertThat(all.size()).isEqualTo(2);

    }

    @Test
    void findSingleTestFromExisting() {
        List<MyDocument> all = repository.findAll();

        Optional<MyDocument> doc1 = all.stream().filter(t -> t.getId().equals(doc1Id)).findFirst();

        logger.info("Find " + doc1);

        assertThat(doc1.isPresent()).isTrue();
        assertThat(doc1.get().getId()).isEqualTo(doc1Id);

    }

    @Test
    void updateSingleTestFromExisting() {

        List<MyDocument> all = repository.findAll();
        Optional<MyDocument> doc1 = all.stream().filter(t -> t.getId().equals(doc1Id)).findFirst();

        logger.info("Find " + doc1);

        assertThat(doc1.isPresent()).isTrue();

        doc1.get().setUpdateElement("new save");

        MyDocument document = repository.save(doc1.get());

        assertThat(document.getId()).isEqualTo(doc1Id);
        assertThat(document.getUpdateElement()).isEqualTo("new save");
        assertThat(document.getCreatedAt()).isNull();
        assertThat(document.getUpdatedAt()).isNotNull();
    }

}
