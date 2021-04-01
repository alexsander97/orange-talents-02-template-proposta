package com.zup.proposta.newTravelNotice;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zup.proposta.card.Card;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class NewTravelNoticeRequest {

    @NotBlank
    private String destiny;

    @NotNull
    @Future
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endTravel;

    public NewTravelNoticeRequest(@NotBlank String destiny, @NotNull @Future LocalDate endTravel) {
        this.destiny = destiny;
        this.endTravel = endTravel;
    }

    public TravelNotice toEntity(Card card, String userAgent, String userIp) {
        return new TravelNotice(card, destiny, endTravel, userAgent, userIp);
    }

    public String getDestiny() {
        return destiny;
    }

    public LocalDate getEndTravel() {
        return endTravel;
    }
}
