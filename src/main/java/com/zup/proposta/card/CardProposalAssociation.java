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


    @Scheduled(cron = "0 0 2 * * *")
//    for test\/
//    @Scheduled(cron = "* * * * * *")
    private void getCard() {
        List<Proposal> proposals = this.repository.findTop1000ByProposalStatusAndCardOrderByCreatedAtAsc(ProposalStatus.ELEGIVEL, null);
        for (Proposal proposal : proposals) {
            CardResponse response = cardClient.getCard(new CardRequest(proposal));
            proposal.associateCard(response);
            repository.save(proposal);
        }
    }
}
