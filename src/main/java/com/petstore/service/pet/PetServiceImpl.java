package com.petstore.service.pet;

import com.petstore.data.model.Pet;
import com.petstore.data.repository.PetRepository;
import com.petstore.web.exceptions.PetDoesNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PetServiceImpl implements PetService {

    @Autowired
    PetRepository petRepository;

    @Override
    public Pet savePet(Pet pet) {

        if(pet == null) {
            throw new NullPointerException("Pet Object can not be null");
        }

        return petRepository.save(pet);
    }

    @Override
    public Pet updatePet(Pet pet) throws PetDoesNotExistException {

        log.info("Pet request Object --> {}", pet);

        Pet savePet = petRepository.findById(pet.getId()).orElse(null);

        if(savePet != null) {
            if (pet.getName() != null) {
                savePet.setName(pet.getName());
            }

            if (pet.getAge() != null) {
                savePet.setAge(pet.getAge());
            }

            if (pet.getColor() != null) {
                savePet.setColor(pet.getColor());
            }

            if (pet.getBreed() != null) {
                savePet.setBreed(pet.getBreed());
            }

            if (pet.getPetSex() != null) {
                savePet.setPetSex(pet.getPetSex());
            }

            log.info("Before saving pet --> {}", savePet);

            return petRepository.save(savePet);
        } else {
            throw new PetDoesNotExistException("Pet with id do not exist ");
        }

    }


    @Override
    public Pet findPetById(Integer id) throws PetDoesNotExistException {

        if (petRepository.existsById(id)) {
            return petRepository.findById(id).get();
        } else {
            throw new PetDoesNotExistException("Pet with the id:" + id + " does not exist");
        }
    }

    @Override
    public List<Pet> findAllPets() {
        return petRepository.findAll();
    }

    @Override
    public void deletePetById(Integer id) throws PetDoesNotExistException {
        try {
            petRepository.deleteById(id);
        } catch (Exception e) {
            throw new PetDoesNotExistException("Pet with the id:"+ id + " does not exist");
        }

    }
}
