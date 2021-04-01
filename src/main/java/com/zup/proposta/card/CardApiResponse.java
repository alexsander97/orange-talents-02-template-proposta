package com.zup.proposta.card;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CardApiResponse {

    @JsonProperty("id")
    private String cardNumber;

    @JsonProperty("titular")
    private String holder;

    @JsonProperty("limite")
    private BigDecimal limit;

    @JsonProperty("emitidoEm")
    private LocalDateTime issuedOn;

    public CardApiResponse(String cardNumber, String holder, BigDecimal limit, LocalDateTime issuedOn) {
        this.cardNumber = cardNumber;
        this.holder = holder;
        this.limit = limit;
        this.issuedOn = issuedOn;
    }

    public Card toEntity() {
        return new Card(cardNumber, holder, limit, issuedOn);
    }

}
