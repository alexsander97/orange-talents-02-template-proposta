package com.zup.proposta.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zup.proposta.builders.AddressRequestBuilder;
import com.zup.proposta.builders.NewProposalRequestBuilder;
import com.zup.proposta.newProposal.Address;
import com.zup.proposta.newProposal.NewProposalRequest;
import com.zup.proposta.newProposal.Proposal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
public class ProposalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private EntityManager entityManager;

//
//    @Test
//    @DisplayName("deve retornar 201 quando os dados tiverem ok")
//    public void test1() throws Exception {
//        NewProposalRequest request = new NewProposalRequestBuilder()
//                .withDocument("54063341070").withEmail("teste@teste.com")
//                .withName("Test")
//                .withAddress(new AddressRequestBuilder()
//                        .withAddress("Rua Test")
//                        .withCep("38408120")
//                        .withNumber("11")
//                        .withComplement("Apto 2000")
//                        .build())
//                .withSalary(new BigDecimal(2000.00)).build();
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/api/proposal")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writeValueAsString(request)))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    @DisplayName("deve retornar 400 quando cpf for inválido")
//    public void test2() throws Exception {
//        NewProposalRequest request = new NewProposalRequestBuilder()
//                .withDocument("11111111111").withEmail("teste@teste.com")
//                .withName("Test")
//                .withAddress(new AddressRequestBuilder()
//                        .withAddress("Rua Test")
//                        .withCep("38408120")
//                        .withNumber("11")
//                        .withComplement("Apto 2000")
//                        .build())
//                .withSalary(new BigDecimal(2000.00)).build();
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/api/proposal")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writeValueAsString(request)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    @DisplayName("deve retornar 422 quando já existir uma proposta para aquele cpf/cpnj")
//    public void test3() throws Exception {
////        cria uma proposta com o cpf que será passado na requisição
//        Proposal proposal = createProposal();
//        entityManager.persist(proposal);
//
//
//        NewProposalRequest request = new NewProposalRequestBuilder()
//                .withDocument("54063341070").withEmail("teste@teste.com")
//                .withName("Test")
//                .withAddress(new AddressRequestBuilder()
//                        .withAddress("Rua Test")
//                        .withCep("38408120")
//                        .withNumber("11")
//                        .withComplement("Apto 2000")
//                        .build())
//                .withSalary(new BigDecimal(2000.00)).build();
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/api/proposal")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writeValueAsString(request)))
//                .andExpect(status().isUnprocessableEntity());
//    }

    private Proposal createProposal() {
        return new Proposal("54063341070", "testando@email.com", "Test",
                new Address("Rua teste", "11", "38408120", "Apto 2000"),
                new BigDecimal(2000.00));
    }


}
