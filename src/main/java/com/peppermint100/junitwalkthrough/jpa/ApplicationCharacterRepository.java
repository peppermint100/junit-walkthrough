package com.peppermint100.junitwalkthrough.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ApplicationCharacterRepository {

    private final EntityManager em;

    public List<ApplicationCharacter> findAll() {
        return em.createQuery(
                "SELECT c FROM ApplicationCharacter c", ApplicationCharacter.class)
                .getResultList();
    }

    public Optional<ApplicationCharacter> findById(int id) {
        ApplicationCharacter character = em.find(ApplicationCharacter.class, id);

        if (character == null) {
            return Optional.empty();
        }

        return Optional.of(character);
    }

    public void saveCharacter(ApplicationCharacter newCharacter) {
        if (newCharacter.getId() == null || findById(newCharacter.getId()).isEmpty()) {
            em.persist(newCharacter);
        } else {
            em.merge(newCharacter);
        }
    }
}
