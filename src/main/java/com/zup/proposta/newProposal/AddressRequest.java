package com.zup.proposta.newProposal;

import javax.validation.constraints.NotBlank;

public class AddressRequest {

    @NotBlank
    private String address;

    @NotBlank
    private String number;

    @NotBlank
    private String cep;

    @NotBlank
    private String complement;

    @Deprecated
    public AddressRequest() {
    }


    public AddressRequest(@NotBlank String address, @NotBlank String number, @NotBlank String cep, @NotBlank String complement) {
        this.address = address;
        this.number = number;
        this.cep = cep;
        this.complement = complement;
    }

    public String getAddress() {
        return address;
    }

    public String getNumber() {
        return number;
    }

    public String getCep() {
        return cep;
    }

    public String getComplement() {
        return complement;
    }

    public Address toAddress() {
        return new Address(address, number, cep, complement);
    }
}
