package com.zup.proposta.newTravelNotice;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.*;

public class TravelNoticeApiRequest {

    @JsonProperty("destino")
    private String destiny;

    @JsonProperty("validoAte")
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd")
    private LocalDate endTravel;

    public TravelNoticeApiRequest(TravelNotice entity) {
        this.destiny = entity.getDestiny();
        this.endTravel = entity.getEndTravel();
    }

    public String getDestiny() {
        return destiny;
    }

    public LocalDate getEndTravel() {
        return endTravel;
    }
}
