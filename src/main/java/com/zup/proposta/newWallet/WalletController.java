package com.zup.proposta.newWallet;

import com.zup.proposta.card.Card;
import com.zup.proposta.card.CardClient;
import com.zup.proposta.newTravelNotice.NewTravelNoticeRequest;
import com.zup.proposta.newTravelNotice.TravelNotice;
import com.zup.proposta.newTravelNotice.TravelNoticeApiRequest;
import com.zup.proposta.newTravelNotice.TravelNoticeApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CardClient cardClient;

    @PostMapping("/card/{idCard}")
    @Transactional
    public ResponseEntity<?> create(@PathVariable Long idCard,
                                    @RequestBody @Valid WalletApiRequest request,
                                    UriComponentsBuilder uriComponentsBuilder) {
        Card card = entityManager.find(Card.class, idCard);
        if (card == null) {
            return ResponseEntity.notFound().build();
        }

        if (card.hasAssociationWallet(request.getTypeAssociationWallet())) {
            return ResponseEntity.unprocessableEntity().build();
        }

        WalletApiResponse response = checkingWalletAssociation(card, request);

        if (response.getResponse().equals(ResultWalletRequest.ASSOCIADA)) {
            Wallet wallet = request.toEntity(card, response.getAssociationId());
            entityManager.persist(wallet);
            URI uri = uriComponentsBuilder.path("/api/wallet/{id}").buildAndExpand(wallet.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }
        return ResponseEntity.badRequest().build();
    }

    private WalletApiResponse checkingWalletAssociation(Card card, WalletApiRequest request) {
        try {
            return cardClient.wallet(card.getCardNumber(), request);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível associar a carteira.");
        }
    }
}
