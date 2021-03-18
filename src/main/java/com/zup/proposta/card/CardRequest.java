package com.zup.proposta.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.proposta.newProposal.Proposal;

public class CardRequest {

    @JsonProperty(value = "documento")
    private String document;

    @JsonProperty(value = "nome")
    private String name;

    @JsonProperty(value = "idProposta")
    private String idProposal;

    public CardRequest(Proposal proposal) {
        this.document = proposal.getDocument();
        this.name = proposal.getName();
        this.idProposal = proposal.getId().toString();
    }
}
