package com.zup.proposta.consultingFinancialAnalysis;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FinancialAnalysisResponse {

    @JsonProperty("resultadoSolicitacao")
    private FinancialAnalysisStatus status;

    public FinancialAnalysisResponse() {
    }

    public FinancialAnalysisResponse(FinancialAnalysisStatus status) {
        this.status = status;
    }

    public FinancialAnalysisStatus getStatus() {
        return status;
    }


    @Override
    public String toString() {
        return "FinancialAnalysisResponse{" +
                "status=" + status +
                '}';
    }
}
