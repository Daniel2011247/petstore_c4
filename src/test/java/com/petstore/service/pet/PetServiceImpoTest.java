package com.petstore.service.pet;

import com.petstore.data.model.Pet;
import com.petstore.data.repository.PetRepository;
import com.petstore.web.exceptions.PetDoesNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@Slf4j
@Sql(scripts = "classpath:db/insert.sql")
class PetServiceImpoTest {

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetService petService = new PetServiceImpl();

    @Autowired
    PetService petServiceImpl;

    @Autowired
    PetRepository petRepositoryImpl;

    Pet testpet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks (this);
        testpet = new Pet();
    }

    @Test
    void callTheSavePetRepositoryTest () {

        when(petRepository.save(testpet)).thenReturn(testpet);
        petService.savePet(testpet);
        verify(petRepository, times(1)).save(testpet);
    }

    @Test
    void mockTheFindByIdRepositoryTest() throws PetDoesNotExistException {
        when(petRepository.findById(31)).thenReturn(Optional.of(testpet));
        petService.findPetById(31);

        verify(petRepository, times(1)).findById(31);
    }

    @Test
    void mockDeletePetRepositoryTest () throws PetDoesNotExistException {
        doNothing().when(petRepository).deleteById(2);
        petService.deletePetById(2);

        verify(petRepository, times(1)).deleteById(2);
    }

    @Test
    void testToFindById() {
        assertThrows(PetDoesNotExistException.class, ()-> petServiceImpl.findPetById(7));
    }

    @Test
    void updatePetDetails () {
        Pet savedPet = petRepositoryImpl.findById(31).orElse(null);
        log.info("Pet object  --> {}", savedPet);

        assertThat(savedPet).isNotNull();

        savedPet.setAge(43);
        petRepositoryImpl.save(savedPet);

        log.info("Pet object now --> {}", savedPet);
    }

}