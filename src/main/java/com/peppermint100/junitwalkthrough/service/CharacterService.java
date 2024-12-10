package com.peppermint100.junitwalkthrough.service;

import com.peppermint100.junitwalkthrough.exception.CharacterDoesNotExistsException;
import com.peppermint100.junitwalkthrough.jpa.ApplicationCharacter;
import com.peppermint100.junitwalkthrough.jpa.ApplicationCharacterRepository;
import com.peppermint100.junitwalkthrough.vo.CharacterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final ApplicationCharacterRepository repository;

    public List<CharacterDto> getAllCharacters() {
        return repository.findAll().stream().map(CharacterDto::from).collect(Collectors.toList());
    }

    public CharacterDto getCharacterById(int id) {
        ApplicationCharacter character = repository.findById(id)
                .orElseThrow(CharacterDoesNotExistsException::new);

        return CharacterDto.from(character);
    }

    public CharacterDto addCharacter(String name) {
        ApplicationCharacter newCharacter = new ApplicationCharacter(name);
        repository.save(newCharacter);
        return CharacterDto.from(newCharacter);
    }

    public void updateCharacter(int id, String newCharacterName) {
        ApplicationCharacter character = repository.findById(id)
                .orElseThrow(CharacterDoesNotExistsException::new);
        character.updateName(newCharacterName);
        repository.save(character);
    }
}
