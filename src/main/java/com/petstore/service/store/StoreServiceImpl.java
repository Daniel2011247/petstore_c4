package com.petstore.service.store;

import com.petstore.data.model.Store;
import com.petstore.data.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    StoreRepository storeRepository;

    @Override
    public Store saveStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public Store updateStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public Store findStoreById(Integer id) {
        return storeRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteStoreById(Integer id) {
        storeRepository.deleteById(id);
    }

    @Override
    public List<Store> findAllStores() {
        return storeRepository.findAll();
    }
}
