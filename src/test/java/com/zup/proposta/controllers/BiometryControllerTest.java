package com.zup.proposta.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zup.proposta.biometry.NewBiometryRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BiometryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;


//    @Test
//    @DisplayName("deve retornar 201 quando os dados tiverem ok")
//    public void test() throws Exception {
//        NewBiometryRequest request = new NewBiometryRequest("testandoo");
//
//        mockMvc.perform(post("/api/card/1/biometry")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writeValueAsString(request)))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    @DisplayName("deve retornar 404 quando cartão não existir")
//    void test2() throws Exception {
//        NewBiometryRequest request = new NewBiometryRequest("testandoo");
//
//        mockMvc.perform(post("/api/cards/100/biometrics")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writeValueAsString(request)))
//                .andExpect(status().isNotFound());
//    }
}
