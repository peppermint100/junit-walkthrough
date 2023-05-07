package com.peppermint100.junitwalkthrough.service;

import com.peppermint100.junitwalkthrough.vo.CharacterDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = {CharacterService.class})
public class CharacterServiceTests {

    @Autowired CharacterService characterService;

    @Test
    @DisplayName("addCharacter 테스트")
    void addCharacterTest() {
        String newCharacterName = "panda";
        List<CharacterDto> allCharacters = characterService.getAllCharacters();
        int sizeBeforeAdd = allCharacters.size();
        CharacterDto newCharacter = characterService.addCharacter(newCharacterName);
        List<CharacterDto> allCharactersAfterAdd = characterService.getAllCharacters();

        assertEquals(newCharacterName, newCharacter.getName());
        assertEquals(sizeBeforeAdd + 1, allCharactersAfterAdd.size());
    }

    @Test
    @DisplayName("getCharacterById 테스트")
    void getCharacterByIdTest() {
        CharacterDto character = characterService.getCharacterById(1);
    }

    @Test
    @DisplayName("updateCharacter 테스트")
    void updateCharacterTest() {
        String newCharacterName = "giraffe";
        characterService.updateCharacter(1, newCharacterName);
    }
}
