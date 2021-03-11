package com.zup.proposta.newProposal;


import com.zup.proposta.consultingFinancialAnalysis.FinancialAnalysisClient;
import com.zup.proposta.consultingFinancialAnalysis.FinancialAnalysisRequest;
import com.zup.proposta.consultingFinancialAnalysis.FinancialAnalysisResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.util.UriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/proposal")
public class ProposalController {

    @Autowired
    private ProposalRepository repository;

    @Autowired
    private FinancialAnalysisClient financialAnalysisClient;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Valid NewProposalRequest request, UriComponentsBuilder uriComponentsBuilder) throws BindException {
        if (repository.existsByDocument(request.getDocument())) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Proposal proposal = request.toEntity();
        repository.save(proposal);

        consultationFinancialAnalysisAndUpdateStatus(proposal);

        URI uri = uriComponentsBuilder.path("/api/proposal/{id}").buildAndExpand(proposal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    private void consultationFinancialAnalysisAndUpdateStatus(Proposal proposal) {
        FinancialAnalysisResponse response = financialAnalysisClient.financialAnalysis(
                new FinancialAnalysisRequest(proposal.getDocument(), proposal.getName(), String.valueOf(proposal.getId())));
        proposal.setProposalStatus(response.getStatus());
        repository.save(proposal);
    }
}
