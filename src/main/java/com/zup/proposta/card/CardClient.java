package com.zup.proposta.card;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "cardClient", url = "${card.targetUrl}")
public interface CardClient {

    @PostMapping("/api/cartoes")
    CardResponse getCard(CardRequest request);
}
