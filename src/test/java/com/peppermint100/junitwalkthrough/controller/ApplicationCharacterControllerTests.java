package com.peppermint100.junitwalkthrough.controller;

import com.google.gson.Gson;
import com.peppermint100.junitwalkthrough.service.CharacterService;
import com.peppermint100.junitwalkthrough.vo.CharacterDto;
import com.peppermint100.junitwalkthrough.vo.RequestCharacter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CharacterController.class)
public class ApplicationCharacterControllerTests {

    @Autowired MockMvc mockMvc;
    @MockBean CharacterService characterService;

    final List<CharacterDto> characters = new ArrayList<>(Arrays.asList(
            new CharacterDto(1, "bear"),
            new CharacterDto(2, "rabbit"),
            new CharacterDto(3, "turtle"),
            new CharacterDto(4, "deer"),
            new CharacterDto(5, "dog"),
            new CharacterDto(6, "cat"),
            new CharacterDto(7, "tiger")
    ));

    @Test
    @DisplayName("[/][GET] - 캐릭터 목록 가져오는 API Test")
    void getCharactersTest() throws Exception {
        given(characterService.getAllCharacters()).willReturn(characters);

        mockMvc.perform(
                get("/characters"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].name").exists())
                .andDo(print());

        verify(characterService).getAllCharacters();
    }

    @Test
    @DisplayName("[/][POST] - 캐릭터 생성 테스트")
    void createCharacterTest() throws Exception {
        String name = "newCharacter";
        CharacterDto newCharacter = new CharacterDto(1, name);

        given(characterService.addCharacter(name)).willReturn(newCharacter);

        RequestCharacter requestBody = new RequestCharacter();
        requestBody.setName(name);

        Gson gson = new Gson();
        String content = gson.toJson(requestBody);

        mockMvc.perform(post("/characters")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andDo(print());

        verify(characterService).addCharacter(name);
    }
}
