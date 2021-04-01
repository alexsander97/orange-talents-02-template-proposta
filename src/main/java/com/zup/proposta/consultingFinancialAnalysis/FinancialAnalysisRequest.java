package com.zup.proposta.consultingFinancialAnalysis;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.proposta.newProposal.Proposal;
import com.zup.proposta.shared.annotations.utils.EncryptorUtils;

public class FinancialAnalysisRequest {

    @JsonProperty(value = "documento")
    private String document;

    @JsonProperty(value = "nome")
    private String name;

    @JsonProperty(value = "idProposta")
    private String idProposal;


    public FinancialAnalysisRequest(Proposal proposal) {
        this.document = EncryptorUtils.decryptingDocument(proposal.getDocument());
        this.name = proposal.getName();
        this.idProposal = String.valueOf(proposal.getId());
    }
}
