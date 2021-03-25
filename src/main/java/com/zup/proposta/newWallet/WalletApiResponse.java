package com.zup.proposta.newWallet;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WalletApiResponse {

    @JsonProperty("resultado")
    private ResultWalletRequest response;

    @JsonProperty("id")
    private String associationId;

    @Deprecated
    public WalletApiResponse() {

    }

    public WalletApiResponse(ResultWalletRequest response, String associationId) {
        this.response = response;
        this.associationId = associationId;
    }

    public ResultWalletRequest getResponse() {
        return response;
    }

    public String getAssociationId() {
        return associationId;
    }
}
