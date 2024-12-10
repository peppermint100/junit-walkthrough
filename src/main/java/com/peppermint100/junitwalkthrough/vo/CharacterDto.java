package com.peppermint100.junitwalkthrough.vo;

import com.peppermint100.junitwalkthrough.jpa.ApplicationCharacter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDto {
    private int id;
    private String name;

    public static CharacterDto withName(String name) {
        CharacterDto characterDto = new CharacterDto();
        characterDto.setName(name);
        return characterDto;
    }

    public static CharacterDto from(ApplicationCharacter character) {
        return new CharacterDto(character.getId(), character.getName());
    }
}
