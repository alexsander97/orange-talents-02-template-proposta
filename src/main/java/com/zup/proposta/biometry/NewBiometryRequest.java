package com.zup.proposta.biometry;

import com.zup.proposta.card.Card;

import javax.validation.constraints.NotEmpty;

public class NewBiometryRequest {

    @NotEmpty
    private String fingerPrint;

    public NewBiometryRequest(@NotEmpty String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    @Deprecated
    public NewBiometryRequest() {
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public Biometry toEntity(Card card) {
        return new Biometry(fingerPrint, card);
    }
}
