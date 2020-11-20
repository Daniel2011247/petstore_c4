package com.petstore.web.controllers.store;

import com.petstore.data.model.Store;
import com.petstore.service.store.StoreService;
import com.petstore.web.exceptions.PetDoesNotExistException;
import com.petstore.web.exceptions.StoreNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequestMapping("/store")
public class StoreRestController {

    @Autowired
    StoreService storeService;

    @PutMapping("/addpet")
    public ResponseEntity<?> addPetToStore(@RequestParam("storeId") String storeId, @RequestParam("petId") String petId) {
        Store store;

        try {
            store = storeService.mapPetToStore(Integer.parseInt(storeId), Integer.parseInt(petId));
        } catch (StoreNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (PetDoesNotExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().body(store);
    }

}
