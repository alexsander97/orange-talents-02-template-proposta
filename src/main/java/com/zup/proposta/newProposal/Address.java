package com.zup.proposta.newProposal;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Address {

    @NotBlank
    private String address;

    @NotBlank
    private String number;

    @NotBlank
    private String cep;

    @NotBlank
    private String complement;

    @Deprecated
    public Address() {

    }

    public Address(String address, String number, String cep, String complement) {
        this.address = address;
        this.number = number;
        this.cep = cep;
        this.complement = complement;
    }
}
