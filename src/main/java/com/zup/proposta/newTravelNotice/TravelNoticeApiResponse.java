package com.zup.proposta.newTravelNotice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TravelNoticeApiResponse {

    @JsonProperty("resultado")
    private String response;

    @Deprecated
    public TravelNoticeApiResponse() {
    }

    public TravelNoticeApiResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }
}
