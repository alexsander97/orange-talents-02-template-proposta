package com.zup.proposta.consultingFinancialAnalysis;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "financialAnalysis", url = "http://localhost:9999/api/solicitacao")
public interface FinancialAnalysisClient {

    @PostMapping
    FinancialAnalysisResponse financialAnalysis(FinancialAnalysisRequest request);
}
