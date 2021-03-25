package com.zup.proposta.blocksCard;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BlockCardApiResponse {

    @JsonProperty("resultado")
    private StatusCard statusCard;

    public BlockCardApiResponse() {}

    public BlockCardApiResponse(StatusCard statusCard) {
        this.statusCard = statusCard;
    }

    public StatusCard getStatusCard() {
        return statusCard;
    }
}
