package com.petstore.web.controllers.pet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petstore.data.model.Gender;
import com.petstore.data.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/snippets")
class PetRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    void whenICallTheCreatePetPostMethod_thenCreateAPetObject () throws Exception {

        Pet pet = new Pet();
        pet.setName("silk");
        pet.setColor("blue");
        pet.setPetSex(Gender.MALE);
        pet.setBreed("cat");
        pet.setAge(7);

        this.mockMvc.perform(post("/pet/create")
                .contentType("application/json")
                .content(mapper.writeValueAsString(pet)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("home"))
                .andReturn();

    }

    @Test
    void whenICallTheGetPetsGetMethod_thenReturnsAllThePetsObject () throws Exception {

        this.mockMvc.perform(get("/pet/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    void whenICallTheFindMethod_thenReturnsThePetObject () throws Exception {

        this.mockMvc.perform(get("/pet/one/31"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    void whenICallTheDeleteMethod_thenItShouldDelete () throws Exception {

        this.mockMvc.perform(delete("/pet/one/31"))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }


}