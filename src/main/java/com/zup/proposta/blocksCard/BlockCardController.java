package com.zup.proposta.blocksCard;

import com.zup.proposta.card.Card;
import com.zup.proposta.card.CardClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/blocks-card")
public class BlockCardController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CardClient cardClient;

    @PostMapping("{idCard}")
    @Transactional
    public ResponseEntity<?> create(@PathVariable Long idCard,
                                    @RequestHeader(HttpHeaders.USER_AGENT) String userAgent,
                                    HttpServletRequest requestInfo) {
        Card card = entityManager.find(Card.class, idCard);
        if (card == null) {
            return ResponseEntity.notFound().build();
        }


        if (card.isBlocked()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        BlockCard blockCard = new BlockCard(requestInfo.getRemoteAddr(), userAgent, card);
        entityManager.persist(blockCard);

        notificationCardClient(card, blockCard.getUserAgent());
        return ResponseEntity.ok().build();
    }

    public void notificationCardClient(Card card, String userAgent) {
        try {
            BlockCardApiResponse response = cardClient.block(card.getCardNumber(), new BlockCardApiRequest(userAgent));
            card.updateStatus(response);
        } catch (Exception e) {
            return;
        }
    }
}
