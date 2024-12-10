package com.peppermint100.junitwalkthrough.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final ApplicationCharacterRepository repository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        List<ApplicationCharacter> applicationCharacterList = List.of(
                new ApplicationCharacter("bear"),
                new ApplicationCharacter("rabbit"),
                new ApplicationCharacter("turtle"),
                new ApplicationCharacter("deer"),
                new ApplicationCharacter("dog"),
                new ApplicationCharacter("cat"),
                new ApplicationCharacter("tiger"));

        for (ApplicationCharacter character : applicationCharacterList) {
            repository.save(character);
        }
    }
}
