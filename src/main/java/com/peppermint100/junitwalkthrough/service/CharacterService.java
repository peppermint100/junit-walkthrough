package com.peppermint100.junitwalkthrough.service;

import com.peppermint100.junitwalkthrough.exception.CharacterDoesNotExistsException;
import com.peppermint100.junitwalkthrough.repository.CharacterRepository;
import com.peppermint100.junitwalkthrough.vo.CharacterDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {

    private final CharacterRepository repository;

    public CharacterService(CharacterRepository repository) {
        this.repository = repository;
    }

    public List<CharacterDto> getAllCharacters() {
        return repository.findAll();
    }

    public CharacterDto getCharacterById(int id) {
        return repository.findById(id)
                .orElseThrow(CharacterDoesNotExistsException::new);
    }

    public CharacterDto addCharacter(String name) {
        CharacterDto newCharacter = CharacterDto.withName(name);
        repository.saveCharacter(newCharacter);
        return newCharacter;
    }

    public void updateCharacter(int id, String newCharacterName) {
        CharacterDto character = repository.findById(id)
                .orElseThrow(CharacterDoesNotExistsException::new);
        character.setName(newCharacterName);
        repository.saveCharacter(character);
    }
}
