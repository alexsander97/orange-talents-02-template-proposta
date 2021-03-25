package com.zup.proposta.card;

import com.zup.proposta.blocksCard.BlockCardApiRequest;
import com.zup.proposta.blocksCard.BlockCardApiResponse;
import com.zup.proposta.newTravelNotice.TravelNoticeApiRequest;
import com.zup.proposta.newTravelNotice.TravelNoticeApiResponse;
import com.zup.proposta.newWallet.WalletApiRequest;
import com.zup.proposta.newWallet.WalletApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "cardClient", url = "${card.targetUrl}")
public interface CardClient {

    @PostMapping("/api/cartoes")
    CardApiResponse getCard(CardApiRequest request);

    @PostMapping("/api/cartoes/{id}/bloqueios")
    BlockCardApiResponse block(@PathVariable String id, BlockCardApiRequest request);

    @PostMapping("/{id}/avisos")
    TravelNoticeApiResponse travelNotice(@PathVariable String id, TravelNoticeApiRequest request);

    @PostMapping("/{id}/carteiras")
    WalletApiResponse wallet(@PathVariable String id, WalletApiRequest request);
}
