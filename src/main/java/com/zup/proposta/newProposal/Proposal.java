package com.zup.proposta.newProposal;

import com.zup.proposta.card.Card;
import com.zup.proposta.card.CardApiResponse;
import com.zup.proposta.consultingFinancialAnalysis.FinancialAnalysisStatus;
import com.zup.proposta.consultingFinancialAnalysis.ProposalStatus;
import com.zup.proposta.shared.annotations.CpfOrCnpj;
import com.zup.proposta.shared.annotations.utils.EncryptorUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String document;

    @NotBlank
    @Email
    @Column(nullable = false)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotNull
    @Embedded
    private Address address;

    @NotNull
    @Positive
    @Column(nullable = false)
    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    private ProposalStatus proposalStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private Card card;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Deprecated
    public Proposal() {

    }

    public Proposal(String document, String email, String name, Address address, BigDecimal salary) {
        this.document = EncryptorUtils.encryptorDocument(document);
        this.email = email;
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.createdAt = LocalDateTime.now();
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

    public ProposalStatus getProposalStatus() {
        return proposalStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Card getCard() {
        return card;
    }

    public void associateCard(CardApiResponse response) {
        this.card = response.toEntity();
    }

    public void updateStatus(FinancialAnalysisStatus status) {
        this.proposalStatus = status.getProposalStatus();
    }

}
