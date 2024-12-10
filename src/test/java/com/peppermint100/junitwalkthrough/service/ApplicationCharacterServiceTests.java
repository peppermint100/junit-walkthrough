package com.peppermint100.junitwalkthrough.service;

import com.peppermint100.junitwalkthrough.exception.CharacterDoesNotExistsException;
import com.peppermint100.junitwalkthrough.jpa.ApplicationCharacterRepository;
import com.peppermint100.junitwalkthrough.vo.CharacterDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ApplicationCharacterServiceTests {

    @Mock private ApplicationCharacterRepository repository;
    private CharacterService service;
//    private AutoCloseable autoCloseable;

    @BeforeEach
    void setupEach() {
        // ExtendWith으로 AutoCloseable 대체 가능
//        autoCloseable = MockitoAnnotations.openMocks(this);
        service = new CharacterService(repository);
    }

//    @AfterEach
//    void teardown() throws Exception {
//        autoCloseable.close();
//    }

    @Test
    @DisplayName("Repository 내부 findAll을 잘 호출하는지 테스트")
    void getAllCharactersTest() {
        final List<CharacterDto> characters = new ArrayList<>(Arrays.asList(
                new CharacterDto(1, "bear"),
                new CharacterDto(2, "rabbit"),
                new CharacterDto(3, "turtle"),
                new CharacterDto(4, "deer"),
                new CharacterDto(5, "dog"),
                new CharacterDto(6, "cat"),
                new CharacterDto(7, "tiger")
        ));

        when(repository.findAll()).thenReturn(characters);
        service.getAllCharacters();
        verify(repository).findAll();
    }

    @Test
    @DisplayName("getCharacterById repository 메소드 호출, id 일치 테스트")
    void getCharacterByIdTest() {
        when(repository.findById(1)).thenReturn(Optional.of(new CharacterDto(1, "giraffe")));

        CharacterDto character = service.getCharacterById(1);

        verify(repository).findById(1);
        assertEquals(1, character.getId());
    }

    @Test
    @DisplayName("getCharacterById Exception 테스트")
    void getCharacterByIdExceptionTest() {
        when(repository.findById(100)).thenThrow(new CharacterDoesNotExistsException());

        assertThrows(CharacterDoesNotExistsException.class, () -> {
            service.getCharacterById(100);
            verify(repository).findById(100);
        });
    }

    @Test
    @DisplayName("addCharacter verify, 이름 테스트")
    void addCharacterTest() {
        String name = "giraffe";

        CharacterDto character = service.addCharacter(name);

        verify(repository).saveCharacter(character);
        assertEquals(name, character.getName());
    }

    @Test
    @DisplayName("존재하지 않는 캐릭터를 updateCharacter")
    void updateCharacterTestInvalid() {
        String name = "giraffe";

        when(repository.findById(100)).thenThrow(new CharacterDoesNotExistsException());

        assertThrows(CharacterDoesNotExistsException.class, () -> {
            service.updateCharacter(100, name);
        });
    }

    @Test
    @DisplayName("updateCharacter 정상작동 테스트")
    void updateCharacterTestValid() {
        String name = "giraffe";

        when(repository.findById(1)).thenReturn(Optional.of(new CharacterDto(1, "giraffe")));
        /*
            ArgumentCaptor를 통해 내부 값을 캡처 후 assertEquals에서 접근
         */
        ArgumentCaptor<CharacterDto> characterCaptor = ArgumentCaptor.forClass(CharacterDto.class);

        service.updateCharacter(1, name);

        verify(repository).findById(1);
        verify(repository).saveCharacter(characterCaptor.capture());

        assertEquals(name, characterCaptor.getValue().getName());
    }
}
