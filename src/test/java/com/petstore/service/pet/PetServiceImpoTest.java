package com.petstore.service.pet;

import com.petstore.data.model.Pet;
import com.petstore.data.repository.PetRepository;
import com.petstore.web.exceptions.PetDoesNotExistException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@Sql(scripts = "classpath:db/insert.sql")
class PetServiceImpoTest {

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetService petService = new PetServiceImpl();

    @Autowired
    PetService petServiceImpl;

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
        when(petRepository.findById(2)).thenReturn(Optional.of(testpet));
        petService.findPetById(2);

        verify(petRepository, times(1)).findById(2);
    }

    @Test
    void mockDeletePetRepositoryTest () {
        doNothing().when(petRepository).deleteById(2);
        petService.deletePetById(2);

        verify(petRepository, times(1)).deleteById(2);
    }

    @Test
    void test() {

        assertThrows(PetDoesNotExistException.class, ()-> petServiceImpl.findPetById(7));
    }

}