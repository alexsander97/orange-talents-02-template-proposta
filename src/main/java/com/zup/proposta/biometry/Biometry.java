package com.zup.proposta.biometry;

import com.zup.proposta.card.Card;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Biometry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String fingerPrint;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false, name = "id_card")
    private Card card;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Deprecated
    public Biometry() {
    }

    public Biometry(String fingerPrint, Card card) {
        this.fingerPrint = fingerPrint;
        this.card = card;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }
}
