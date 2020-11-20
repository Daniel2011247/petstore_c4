package com.petstore.service.pet;

import com.petstore.data.model.Pet;
import com.petstore.data.repository.PetRepository;
import com.petstore.web.exceptions.PetDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public Pet updatePet(Pet pet) {
        return null;
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
    public void deletePetById(Integer id) {
        petRepository.deleteById(id);
    }
}
