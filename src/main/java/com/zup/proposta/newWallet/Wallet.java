package com.zup.proposta.newWallet;

import com.zup.proposta.card.Card;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String associationId;

    @NotBlank
    @Column(nullable = false)
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeAssociationWallet typeAssociationWallet;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_card", nullable = false)
    private Card card;

    public Wallet(@NotBlank String associationId, @NotBlank String email,
                  @NotNull TypeAssociationWallet typeAssociationWallet, @NotNull Card card) {
        this.associationId = associationId;
        this.email = email;
        this.typeAssociationWallet = typeAssociationWallet;
        this.card = card;
    }

    public Long getId() {
        return id;
    }

    public TypeAssociationWallet getTypeAssociationWallet() {
        return typeAssociationWallet;
    }

    @Deprecated
    public Wallet() {
    }

}
