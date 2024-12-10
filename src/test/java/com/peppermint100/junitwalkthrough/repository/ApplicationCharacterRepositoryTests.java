package com.peppermint100.junitwalkthrough.repository;

import com.peppermint100.junitwalkthrough.jpa.ApplicationCharacter;
import com.peppermint100.junitwalkthrough.jpa.ApplicationCharacterRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public class ApplicationCharacterRepositoryTests {

    @Autowired private ApplicationCharacterRepository repository;

    @BeforeAll
    void setupTest() {
        repository.save(new ApplicationCharacter("giraffe"));
    }

    @Test
    void checkGiraffeExists() {
        boolean giraffe = repository.existsByName("giraffe");
        assertTrue(giraffe);
    }

    @Test
    void checkRabbitDoesNotExists() {
        boolean rabbit = repository.existsByName("rabbit");
        assertFalse(rabbit);
    }
}
