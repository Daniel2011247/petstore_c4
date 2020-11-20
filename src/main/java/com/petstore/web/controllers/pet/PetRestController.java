package com.petstore.web.controllers.pet;

import com.petstore.data.model.Pet;
import com.petstore.service.pet.PetService;
import com.petstore.web.exceptions.PetDoesNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
@Slf4j
public class PetRestController {

    @Autowired
    PetService petService;

    @PostMapping("/create")
    public ResponseEntity<?> savePet(@RequestBody Pet pet) {

        log.info("Request object --> {}", pet);

        try {
            petService.savePet(pet);
        } catch (NullPointerException exe) {
            return ResponseEntity.badRequest().body(exe.getMessage());
        }

        return new ResponseEntity<>(pet, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPets() {

        log.info("Get endpoint called");

        return ResponseEntity.ok().body(petService.findAllPets());
    }

    @GetMapping("one/{id}")
    public ResponseEntity<?> findPetById (@PathVariable Integer id) {

        log.info("Id of pet to be found --> {}", id);

        Pet pet;
        try {
            pet = petService.findPetById(id);
        } catch (PetDoesNotExistException e) {
            e.printStackTrace();
            return  ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().body(pet);
    }

    @DeleteMapping("/one/{id}")
    public ResponseEntity<?> deletePet(@PathVariable Integer id) {
        try {
            petService.deletePetById(id);
        } catch (PetDoesNotExistException e) {
            return ResponseEntity.badRequest().body(e);
        }

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/one/{id}")
    public @ResponseBody ResponseEntity<?> updatePetById (@RequestBody Pet pet) {
        try {
            petService.updatePet(pet);

        } catch (PetDoesNotExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().body(pet);
    }

}
