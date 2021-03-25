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
import java.util.Optional;

@RestController
@RequestMapping("/api/proposal")
public class ProposalController {

    @Autowired
    private ProposalRepository repository;

    @Autowired
    private FinancialAnalysisClient financialAnalysisClient;

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Valid NewProposalRequest request, UriComponentsBuilder uriComponentsBuilder)  {
        if (repository.existsByDocument(request.getDocument())) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Proposal proposal = request.toEntity();
        repository.save(proposal);

        consultationFinancialAnalysisAndUpdateStatus(proposal);

        URI uri = uriComponentsBuilder.path("/api/proposal/{id}").buildAndExpand(proposal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        Optional<Proposal> proposal = this.repository.findById(id);
        if(!proposal.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(new ProposalResponse(proposal.get()));
    }

    private void consultationFinancialAnalysisAndUpdateStatus(Proposal proposal) {
        FinancialAnalysisResponse response = financialAnalysisClient.financialAnalysis(
                new FinancialAnalysisRequest(proposal));
        proposal.updateStatus(response.getStatus());
        repository.save(proposal);
    }
}
