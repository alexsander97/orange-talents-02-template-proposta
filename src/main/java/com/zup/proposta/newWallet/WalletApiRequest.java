package com.zup.proposta.newWallet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.proposta.card.Card;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class WalletApiRequest {

    @Email
    @NotBlank
    private String email;

    @NotNull
    @JsonProperty("carteira")
    private TypeAssociationWallet typeAssociationWallet;

    public WalletApiRequest(String email, TypeAssociationWallet typeAssociationWallet) {
        this.email = email;
        this.typeAssociationWallet = typeAssociationWallet;
    }


    public String getEmail() {
        return email;
    }

    public TypeAssociationWallet getTypeAssociationWallet() {
        return typeAssociationWallet;
    }

    public Wallet toEntity(Card card, String associationId) {
        return new Wallet(associationId, email, typeAssociationWallet, card);
    }
}
