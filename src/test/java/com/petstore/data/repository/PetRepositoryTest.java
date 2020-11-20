package com.petstore.data.repository;

import com.petstore.data.model.Gender;
import com.petstore.data.model.Pet;
import com.petstore.data.model.Store;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Sql(scripts = {"classpath:db/insert.sql"})
class PetRepositoryTest {
    
    @Autowired
    PetRepository petRepository;

    @Autowired
    StoreRepository storeRepository;

    @BeforeEach
    void setUp() {
        
    }
    
    @Test
    public void whenPetIsSaved_theReturnPetId() {
        Pet pet = new Pet();
        pet.setName("jack");
        pet.setAge(2);
        pet.setBreed("Dog");
        pet.setColor("Black");
        pet.setPetSex(Gender.MALE);

        log.info("Pet instance before saving --> {}", pet);
        petRepository.save(pet);

        assertThat(pet.getId()).isNotNull();
        log.info("Pet instance after saving --> {}", pet);

    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void whenStoreIsMappedToPet_thenForeignKeyIsPresent() {

        Store store = new Store();
        store.setName("Pet Sellers");
        store.setLocation("Yaba");
        store.setContactNo("09031861100");

        Pet pet = new Pet();
        pet.setName("jack");
        pet.setAge(2);
        pet.setBreed("Dog");
        pet.setColor("Black");
        pet.setPetSex(Gender.MALE);

        log.info("Pet instance before saving --> {}", pet);

        pet.setStore(store);

        petRepository.save(pet);

        log.info("Pet instance before saving --> {}", pet);
        log.info("Store instance after saving --> {}", store);
        assertThat(pet.getId()).isNotNull();
        assertThat(store.getId()).isNotNull();
        assertThat(pet.getStore()).isNotNull();

    }


    @Transactional
    @Rollback(value = false)
    @Test
    public void whenIAddPetsToAStore_thenICan () {
        Store store = new Store();
        store.setName("Pet Sellers");
        store.setLocation("Yaba");
        store.setContactNo("09031861100");

        Pet jack = new Pet();
        jack.setName("jack");
        jack.setAge(5);
        jack.setBreed("Dog");
        jack.setColor("Black");
        jack.setPetSex(Gender.MALE);
        jack.setStore(store);

        Pet sally = new Pet();
        sally.setName("sally");
        sally.setAge(2);
        sally.setBreed("Dog");
        sally.setColor("Brown");
        sally.setPetSex(Gender.FEMALE);
        sally.setStore(store);

        log.info("Pet instance before saving --> {}", jack);
        log.info("Pet instance before saving --> {}", sally);

        store.addPets(sally);
        store.addPets(jack);

        storeRepository.save(store);

        log.info("Pet instance before saving --> {}", jack);
        log.info("Pet instance before saving --> {}", sally);
        log.info("Store instance before saving --> {}", store);

        assertThat(jack.getId()).isNotNull();
        assertThat(sally.getId()).isNotNull();
        assertThat(store.getId()).isNotNull();

        assertThat(store.getPetList()).isNotNull();
    }


    @Test
    public void whenFindAllPetIsCalled_thenReturnAllPetsInStore () {
        List<Pet> savedPets = petRepository.findAll();

        log.info("Fetched pets list from db --> {}", savedPets);

        assertThat(savedPets).isNotEmpty();
        assertThat(savedPets.size()).isEqualTo(4);

        log.info("Fetched pets list from db --> {}", savedPets);
    }

    @Test
    public void updateExistingPetDetailsTest() {
        Pet pet = petRepository.findById(11).orElse(null);

        assertThat(pet).isNotNull();

        assertThat(pet.getColor()).isEqualTo("white");

        pet.setColor("purple");

        petRepository.save(pet);
        assertThat(pet.getColor()).isEqualTo("purple");

        log.info("hello -> {}", pet);

    }

    @Test
    public void whenIDeletePetFromDatabase_thenPetIsDeleted() {
        try {
            petRepository.deleteById(10);
            assertThat(petRepository.existsById(10)).isFalse();
        } catch (Exception e ) {
            log.info("Pet does not exist exception was thrown");
        }
    }
}