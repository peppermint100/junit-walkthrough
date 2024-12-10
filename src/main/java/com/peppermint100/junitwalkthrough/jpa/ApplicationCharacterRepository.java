package com.peppermint100.junitwalkthrough.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationCharacterRepository extends JpaRepository<ApplicationCharacter, Integer> {
    boolean existsByName(String name);
}
