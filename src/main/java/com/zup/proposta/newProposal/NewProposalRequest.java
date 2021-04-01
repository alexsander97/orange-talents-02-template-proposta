package com.zup.proposta.newProposal;

import com.zup.proposta.shared.annotations.CpfOrCnpj;
import com.zup.proposta.shared.annotations.utils.EncryptorUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NewProposalRequest {

    @NotBlank
    @CpfOrCnpj
    private String document;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String name;

    @NotNull
    private AddressRequest address;

    @NotNull
    @Positive
    private BigDecimal salary;

    @Deprecated
    public NewProposalRequest() {
    }

    public NewProposalRequest(@NotBlank String document, @NotBlank @Email String email,
                              @NotBlank String name, @NotNull AddressRequest address,
                              @NotNull @Positive BigDecimal salary) {
        this.document = document;
        this.email = email;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public String getDocument() {
        return document;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public AddressRequest getAddress() {
        return address;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public Proposal toEntity() {
        return new Proposal(document, email, name, address.toAddress(), salary);
    }
}
