package com.petstore.web.controllers.store;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petstore.service.store.StoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StoreRestControllerTest {

    @Autowired
    StoreService storeService;

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    void addPetToStoreTest() throws Exception {
        this.mockMvc.perform(put("/store/addpet").param("storeId", "21").param( "petId", "10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}