package com.zup.proposta.newProposal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.proposta.card.Card;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CardDetailsResponse {

    private String cardNumber;

    private String holder;

    private BigDecimal limit;

    private LocalDateTime issuedOn;

    public CardDetailsResponse(Card card) {
        this.cardNumber = card.getCardNumber();
        this.holder = card.getHolder();
        this.limit = card.getLimit();
        this.issuedOn = card.getIssuedOn();
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getHolder() {
        return holder;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public LocalDateTime getIssuedOn() {
        return issuedOn;
    }
}
