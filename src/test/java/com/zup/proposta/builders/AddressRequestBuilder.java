package com.zup.proposta.builders;


import com.zup.proposta.newProposal.AddressRequest;

public class AddressRequestBuilder {

    private String address;

    private String number;

    private String cep;

    private String complement;

    public AddressRequestBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public AddressRequestBuilder withNumber(String number) {
        this.number = number;
        return this;
    }

    public AddressRequestBuilder withCep(String cep) {
        this.cep = cep;
        return this;
    }

    public AddressRequestBuilder withComplement(String complement) {
        this.complement = complement;
        return this;
    }
    public AddressRequest build() {
        return new AddressRequest(address, number, cep, complement);
    }
}
