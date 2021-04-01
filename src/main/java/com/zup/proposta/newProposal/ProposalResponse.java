package com.zup.proposta.newProposal;



import com.zup.proposta.consultingFinancialAnalysis.ProposalStatus;

import java.time.LocalDateTime;

public class ProposalResponse {


    private String document;
    private ProposalStatus proposalStatus;
    private CardDetailsResponse card;
    private LocalDateTime createdAt;


    public ProposalResponse(Proposal proposal) {
        this.document = proposal.getDocument();
        this.proposalStatus = proposal.getProposalStatus();
        this.createdAt = proposal.getCreatedAt();
        if (proposal.getCard() != null) {
            this.card = new CardDetailsResponse(proposal.getCard());
        }
    }

    public String getDocument() {
        return document;
    }

    public CardDetailsResponse getCard() {
        return card;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public ProposalStatus getProposalStatus() {
        return proposalStatus;
    }
}
