package com.zup.proposta.card;

import com.zup.proposta.consultingFinancialAnalysis.ProposalStatus;
import com.zup.proposta.newProposal.Proposal;
import com.zup.proposta.newProposal.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardProposalAssociation {

    @Autowired
    private CardClient cardClient;

    @Autowired
    private ProposalRepository repository;


    @Scheduled(cron = "${CRON_CARD_PROPOSAL_ASSOCIATION}")
    private void getCard() {
        List<Proposal> proposals = this.repository.findTop1000ByProposalStatusAndCardOrderByCreatedAtAsc(ProposalStatus.ELEGIVEL, null);
        for (Proposal proposal : proposals) {
            try {
                CardApiResponse response = cardClient.getCard(new CardApiRequest(proposal));
                proposal.associateCard(response);
                repository.save(proposal);
            } catch (Exception e) {
                return;
            }
        }
    }
}
