package com.peppermint100.junitwalkthrough.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ApplicationCharacterRepository extends JpaRepository<ApplicationCharacter, Integer> {
}
