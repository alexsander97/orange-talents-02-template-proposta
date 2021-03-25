package com.zup.proposta.blocksCard;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BlockCardApiRequest {

    @JsonProperty("sistemaResponsavel")
    private String responsibleSystem;

    @Deprecated
    public BlockCardApiRequest() {

    }

    public BlockCardApiRequest(String responsibleSystem) {
        this.responsibleSystem = responsibleSystem;
    }

    public String getResponsibleSystem() {
        return responsibleSystem;
    }
}
