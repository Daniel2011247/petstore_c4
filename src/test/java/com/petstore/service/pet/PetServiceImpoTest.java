package com.petstore.service.pet;

import com.petstore.data.model.Pet;
import com.petstore.data.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PetServiceImpoTest {

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetService petService = new PetServiceImpl();

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
    void mockTheFindByIdRepositoryTest() {
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


}