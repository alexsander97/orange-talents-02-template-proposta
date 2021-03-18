package com.zup.proposta.newProposal;

import com.zup.proposta.card.Card;
import com.zup.proposta.consultingFinancialAnalysis.ProposalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {

    boolean existsByDocument(String document);

    List<Proposal> findTop1000ByProposalStatusAndCardOrderByCreatedAtAsc(ProposalStatus status, Card card);
}
