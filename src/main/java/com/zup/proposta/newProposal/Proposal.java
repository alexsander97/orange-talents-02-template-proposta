package com.zup.proposta.newProposal;

import com.zup.proposta.card.Card;
import com.zup.proposta.card.CardResponse;
import com.zup.proposta.consultingFinancialAnalysis.FinancialAnalysisStatus;
import com.zup.proposta.consultingFinancialAnalysis.ProposalStatus;
import com.zup.proposta.shared.annotations.CpfOrCnpj;
import org.springframework.security.crypto.encrypt.Encryptors;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private Card card;

    private LocalDateTime createdAt;

    @Deprecated
    public Proposal() {

    }

    public Proposal(String document, String email, String name, Address address, BigDecimal salary) {
        this.document = document;
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

    public void associateCard(CardResponse response) {
        this.card = response.toEntity();
    }

    public void updateStatus(FinancialAnalysisStatus status) {
        this.proposalStatus = status.getProposalStatus();
    }

    @Override
    public String toString() {
        return "Proposal{" +
                "id=" + id +
                ", document='" + document + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", salary=" + salary +
                ", proposalStatus=" + proposalStatus +
                ", card=" + card +
                '}';
    }
}
