package action.in.blog.client;

import action.in.blog.domain.ProductResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProductClient {

    private final WebClient webClient;

    public ProductClient(@Value("${product.service}") String baseUrl) {
        webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<ProductResponse> getProduct(@PathVariable int id) {
        return webClient
                .get()
                .uri("{id}", id)
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .onErrorResume(ex -> Mono.empty());
    }
}
