package com.zup.proposta.newTravelNotice;

import com.zup.proposta.card.Card;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class TravelNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_card")
    private Card card;

    @NotBlank
    private String destiny;

    @NotNull
    @Future
    private LocalDate endTravel;

    @NotBlank
    private String userAgent;

    @NotBlank
    private String userIp;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public TravelNotice(Card card, String destiny, LocalDate endTravel, String userAgent, String userIp) {
        this.card = card;
        this.destiny = destiny;
        this.endTravel = endTravel;
        this.userAgent = userAgent;
        this.userIp = userIp;
        this.createdAt = LocalDateTime.now();
    }

    public String getDestiny() {
        return destiny;
    }

    public LocalDate getEndTravel() {
        return endTravel;
    }
}
