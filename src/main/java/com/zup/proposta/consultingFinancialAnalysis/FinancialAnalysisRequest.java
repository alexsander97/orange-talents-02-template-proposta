package com.zup.proposta.consultingFinancialAnalysis;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FinancialAnalysisRequest {

    @JsonProperty(value = "documento")
    private String document;

    @JsonProperty(value = "nome")
    private String name;

    @JsonProperty(value = "idProposta")
    private String idProposal;

    public FinancialAnalysisRequest(String document, String name, String idProposal) {
        this.document = document;
        this.name = name;
        this.idProposal = idProposal;
    }
}
