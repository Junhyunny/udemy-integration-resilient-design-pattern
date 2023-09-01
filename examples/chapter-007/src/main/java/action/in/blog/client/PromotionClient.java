package action.in.blog.client;

import action.in.blog.domain.PromotionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PromotionClient {

    private final WebClient webClient;

    public PromotionClient(@Value("${promotion.service}") String baseUrl) {
        webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<PromotionResponse> getPromotion(int id) {
        return this.webClient
                .get()
                .uri("{id}", id)
                .retrieve()
                .bodyToMono(PromotionResponse.class)
                .onErrorResume(ex -> Mono.empty())
                .switchIfEmpty(Mono.just(PromotionResponse.noPromotion()));
    }
}
