package com.zup.proposta.newProposal;


import com.zup.proposta.consultingFinancialAnalysis.FinancialAnalysisClient;
import com.zup.proposta.consultingFinancialAnalysis.FinancialAnalysisRequest;
import com.zup.proposta.consultingFinancialAnalysis.FinancialAnalysisResponse;
import com.zup.proposta.consultingFinancialAnalysis.FinancialAnalysisStatus;
import com.zup.proposta.shared.annotations.utils.EncryptorUtils;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;
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

        if (repository.existsByDocument(EncryptorUtils.encryptorDocument(request.getDocument()))) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Proposal proposal = request.toEntity();
        repository.save(proposal);

        FinancialAnalysisStatus financialAnalysisStatus = consultationFinancialAnalysis(proposal);
        proposal.updateStatus(financialAnalysisStatus);


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

    private FinancialAnalysisStatus consultationFinancialAnalysis(Proposal proposal) {
        try {
            FinancialAnalysisResponse response = financialAnalysisClient.financialAnalysis(
                    new FinancialAnalysisRequest(proposal));
            return response.getStatus();
        } catch (FeignException.UnprocessableEntity e) {
            return FinancialAnalysisStatus.COM_RESTRICAO;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Um erro inesperado aconteceu, tente novamente mais tarde.");
        }
    }
}
