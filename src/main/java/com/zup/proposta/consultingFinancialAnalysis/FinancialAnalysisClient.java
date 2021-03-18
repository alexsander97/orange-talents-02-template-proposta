package com.zup.proposta.consultingFinancialAnalysis;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "financialAnalysis", url = "${financialAnalysis.targetUrl}")
public interface FinancialAnalysisClient {

    @PostMapping("/api/solicitacao")
    FinancialAnalysisResponse financialAnalysis(FinancialAnalysisRequest request);
}
