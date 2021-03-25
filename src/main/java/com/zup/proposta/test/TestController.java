package com.zup.proposta.test;

import com.zup.proposta.consultingFinancialAnalysis.FinancialAnalysisClient;
import com.zup.proposta.consultingFinancialAnalysis.FinancialAnalysisRequest;
import com.zup.proposta.consultingFinancialAnalysis.FinancialAnalysisResponse;
import com.zup.proposta.newProposal.Address;
import com.zup.proposta.newProposal.Proposal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private FinancialAnalysisClient client;

    @PersistenceContext
    private EntityManager entityManager;


    @GetMapping
    @Transactional
    public List<String> teste(@RequestHeader(HttpHeaders.USER_AGENT) String userAgent,
                              HttpServletRequest requestInfo) {
        System.out.println(userAgent);
//        System.out.println(authorization);
//        System.out.println(requestInfo.getLocalName());
//        System.out.println(requestInfo.getLocalPort());
//        System.out.println(requestInfo.getRemoteAddr());
//        System.out.println(requestInfo.getRequestURL());
        Proposal proposal = new Proposal("11840251697", "alex@hotmail.com", "Test",
                new Address("Rua X", "11", "38408120", "apto 22"), new BigDecimal(200.0));
        entityManager.persist(proposal);
        FinancialAnalysisResponse response = client.financialAnalysis(new FinancialAnalysisRequest(proposal));

        System.out.println(response.toString());


        List<String> strings = Arrays.asList("Não", "Consigo", "Fazer", "Requisição");
        System.out.println(strings);
        LOGGER.info(strings.stream().reduce("", (e, a) -> e + a));
        return strings;
    }

    @GetMapping("/test")
    public List<String> teste2(@RequestHeader(HttpHeaders.USER_AGENT) String userAgent,
                               @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
                               HttpServletRequest requestInfo) {
        System.out.println(userAgent);
        System.out.println(authorization);
        System.out.println(requestInfo.getLocalName());
        System.out.println(requestInfo.getLocalPort());
        System.out.println(requestInfo.getRemoteAddr());
        System.out.println(requestInfo.getRequestURL());
        List<String> strings = Arrays.asList("Não", "Consigo", "Fazer", "Requisição");
        System.out.println(strings);
        LOGGER.info(strings.stream().reduce("", (e, a) -> e + a));
        return strings;
    }
}
