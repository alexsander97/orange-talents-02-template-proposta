package com.zup.proposta.consultingFinancialAnalysis;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FinancialAnalysisResponse {

    @JsonProperty("resultadoSolicitacao")
    private FinancialAnalysisStatus status;

    public FinancialAnalysisStatus getStatus() {
        return status;
    }
}
