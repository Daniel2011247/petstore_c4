package com.petstore.data.repository;

import static org.assertj.core.api.Assertions.assertThat;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/store-db.sql"})
class StoreRepositoryTest {

    @Autowired
    StoreRepository storeRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void saveStoreToDb() {
        Store store = new Store();

        store.setName("iClass Pet Store");
        store.setContactNo("09031861100");
        store.setLocation("Sabo, Yaba");

        log.info("Store before saving -> {}", store);

        storeRepository.save(store);

        log.info("Store after saving -> {}", store);

       assertThat(store.getId()).isNotNull();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void findAllTHeStores() {
       List<Store> stores = storeRepository.findAll();

       assertThat(stores).isNotEmpty();

       log.info("Stores -> {}", stores);
    }

    @Test
    void insertStoreIntoDb_thenFindStore() {
        Store store = storeRepository.findById(10).orElse(null);

        assertThat(store).isNotNull();

        log.info("store -> {}", store.getName());
    }

    @Test
    void updateExistingStore() {
        Store store = storeRepository.findById(10).orElse(null);

        assertThat(store).isNotNull();
        assertThat(store.getLocation()).isEqualTo("Enugu");

        store.setLocation("Lagos");

        storeRepository.save(store);

        assertThat(store.getLocation()).isEqualTo("Lagos");


        log.info("store location -> {}", store.getLocation());
    }

    @Test
    void deleteAStore() {
        assertThat(storeRepository.existsById(10)).isTrue();
        storeRepository.deleteById(10);
        assertThat(storeRepository.existsById(10)).isFalse();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void findAllPetsForAStore() {
        Store store = storeRepository.findById(21).orElse(null);

        assertThat(store).isNotNull();
        assertThat(store.getPetList().size()).isEqualTo(4);

        log.info("store -> {}", store.getPetList());
    }
}