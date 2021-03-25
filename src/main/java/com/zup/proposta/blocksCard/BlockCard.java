package com.zup.proposta.blocksCard;

import com.zup.proposta.card.Card;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BlockCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ipUser;

    private String userAgent;

    private LocalDateTime createdAt;

    @OneToOne
    private Card card;

    public BlockCard(String ip, String userAgent, Card card) {
        this.ipUser = ip;
        this.userAgent = userAgent;
        this.card = card;
        this.createdAt = LocalDateTime.now();
    }

    public String getUserAgent() {
        return userAgent;
    }
}
