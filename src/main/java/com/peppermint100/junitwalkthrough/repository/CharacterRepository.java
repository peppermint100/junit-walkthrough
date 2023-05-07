package com.peppermint100.junitwalkthrough.repository;

import com.peppermint100.junitwalkthrough.vo.CharacterDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class CharacterRepository {
    private static final List<CharacterDto> characters = new ArrayList<>(Arrays.asList(
            new CharacterDto(1, "bear"),
            new CharacterDto(2, "rabbit"),
            new CharacterDto(3, "turtle"),
            new CharacterDto(4, "deer"),
            new CharacterDto(5, "dog"),
            new CharacterDto(6, "cat"),
            new CharacterDto(7, "tiger")
    ));

    public List<CharacterDto> findAll() {
        return characters;
    }

    public Optional<CharacterDto> findById(int id) {
        for (CharacterDto character : characters) {
            if (character.getId() == id) {
                return Optional.of(character);
            }
        }

        return Optional.empty();
    }

    public void saveCharacter(CharacterDto newCharacter) {
        this.findById(newCharacter.getId())
            .ifPresentOrElse(
                character -> character.setName(newCharacter.getName()),
                () -> {
                    int size = this.findAll().size();
                    newCharacter.setId(size+1);
                    characters.add(newCharacter);
                }
            );
    }
}
