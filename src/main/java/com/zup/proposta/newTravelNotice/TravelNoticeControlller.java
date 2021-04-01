package com.zup.proposta.newTravelNotice;


import com.zup.proposta.card.Card;
import com.zup.proposta.card.CardClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/travel-notice")
public class TravelNoticeControlller {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CardClient cardClient;

    @PostMapping("/card/{idCard}")
    @Transactional
    public ResponseEntity<?> create(@PathVariable Long idCard,
                                    @RequestHeader(HttpHeaders.USER_AGENT) String userAgent,
                                    @RequestBody @Valid NewTravelNoticeRequest request,
                                    HttpServletRequest httpRequest) {
        Card card = entityManager.find(Card.class, idCard);
        if (card == null) {
            return ResponseEntity.notFound().build();
        }

        TravelNotice travelNotice = request.toEntity(card, userAgent, httpRequest.getRemoteAddr());
        notificationCardClient(card, travelNotice);
        return ResponseEntity.ok().build();
    }

    public void notificationCardClient(Card card, TravelNotice travelNotice) {
        try {
            TravelNoticeApiResponse response = cardClient.travelNotice(card.getCardNumber(), new TravelNoticeApiRequest(travelNotice));
            entityManager.persist(travelNotice);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorrou um erro ao processar sua solicitação.");
        }
    }
}
