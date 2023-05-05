package com.peppermint100.junitwalkthrough.controller;

import com.peppermint100.junitwalkthrough.service.CharacterService;
import com.peppermint100.junitwalkthrough.vo.CharacterDto;
import com.peppermint100.junitwalkthrough.vo.RequestCharacter;
import com.peppermint100.junitwalkthrough.vo.RequestModifyCharacter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterService service;

    public CharacterController(CharacterService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CharacterDto>> getCharacters() {
        List<CharacterDto> characters = service.getAllCharacters();
        return ResponseEntity.ok(characters);
    }

    @PostMapping
    public ResponseEntity<CharacterDto> createCharacter(
            @RequestBody RequestCharacter requestCharacter
            ) {
        String name = requestCharacter.getName();
        CharacterDto character = service.addCharacter(name);
        return ResponseEntity.ok(character);
    }

    @PutMapping("/{id}")
    public ResponseEntity modifyCharacter(
            @PathVariable("id") Integer id,
            @RequestBody RequestCharacter requestCharacter
    ) {
        String newName = requestCharacter.getName();
        service.updateCharacter(id, newName);
        return ResponseEntity.status(200).build();
    }
}
