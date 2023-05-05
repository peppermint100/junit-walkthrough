package com.peppermint100.junitwalkthrough.service;

import com.peppermint100.junitwalkthrough.exception.CharacterDoesNotExistsException;
import com.peppermint100.junitwalkthrough.vo.CharacterDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CharacterService {
    private static final List<CharacterDto> characters = new ArrayList<>(Arrays.asList(
            new CharacterDto(1, "bear"),
            new CharacterDto(2, "rabbit"),
            new CharacterDto(3, "turtle"),
            new CharacterDto(4, "deer"),
            new CharacterDto(5, "dog"),
            new CharacterDto(6, "cat"),
            new CharacterDto(7, "tiger")
    ));

    public List<CharacterDto> getAllCharacters() {
        return characters;
    }

    public CharacterDto addCharacter(String name) {
        CharacterDto lastCharacter = characters.get(characters.size() - 1);
        CharacterDto newCharacter = new CharacterDto(lastCharacter.getId() + 1, name);
        characters.add(newCharacter);
        return newCharacter;
    }

    public void updateCharacter(int id, String newCharacterName) {
        for (CharacterDto character : characters) {
            if (character.getId() == id) {
                character.setName(newCharacterName);
                return;
            }
        }

        throw new CharacterDoesNotExistsException();
    }
}
