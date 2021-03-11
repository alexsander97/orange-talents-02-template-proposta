package com.zup.proposta.builders;

import com.zup.proposta.newProposal.AddressRequest;
import com.zup.proposta.newProposal.NewProposalRequest;

import java.math.BigDecimal;

public class NewProposalRequestBuilder {

    private String document;

    private String email;

    private String name;

    private AddressRequest address;

    private BigDecimal salary;

    public NewProposalRequestBuilder withDocument(String document) {
        this.document = document;
        return this;
    }

    public NewProposalRequestBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public NewProposalRequestBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public NewProposalRequestBuilder withAddress(AddressRequest address) {
        this.address = address;
        return this;
    }

    public NewProposalRequestBuilder withSalary(BigDecimal salary) {
        this.salary = salary;
        return this;
    }

    public NewProposalRequest build() {
        return new NewProposalRequest(document, email, name, address, salary);
    }

}
