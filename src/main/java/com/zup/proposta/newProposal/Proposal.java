package com.zup.proposta.newProposal;

import com.zup.proposta.consultingFinancialAnalysis.FinancialAnalysisStatus;
import com.zup.proposta.consultingFinancialAnalysis.ProposalStatus;
import com.zup.proposta.shared.annotations.CpfOrCnpj;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @CpfOrCnpj
    private String document;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String name;

    @NotNull
    @Embedded
    private Address address;

    @NotNull
    @Positive
    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    private ProposalStatus proposalStatus;

    @Deprecated
    public Proposal() {

    }

    public Proposal(String document, String email, String name, Address address, BigDecimal salary) {
        this.document = document;
        this.email = email;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public String getDocument() {
        return document;
    }

    public String getName() {
        return name;
    }
    
    public void setProposalStatus(FinancialAnalysisStatus status) {
        this.proposalStatus = status.getProposalStatus();
    }
}
