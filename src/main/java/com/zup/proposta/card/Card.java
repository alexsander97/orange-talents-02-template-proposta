package com.zup.proposta.card;

import com.zup.proposta.biometry.Biometry;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private String holder;

    @Column(name = "card_limit", nullable = false)
    private BigDecimal limit;

    @Column(nullable = false)
    private LocalDateTime issuedOn;

    @OneToMany(mappedBy = "card")
    private Set<Biometry> biometrics = new HashSet<>();


    @Deprecated
    public Card() {

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

    public Card(String cardNumber, String holder, BigDecimal limit, LocalDateTime issuedOn) {
        this.cardNumber = cardNumber;
        this.holder = holder;
        this.limit = limit;
        this.issuedOn = issuedOn;
    }
}
