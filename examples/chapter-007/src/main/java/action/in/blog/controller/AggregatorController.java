package action.in.blog.controller;

import action.in.blog.aggregator.ProductAggregatorService;
import action.in.blog.domain.ProductAggregator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class AggregatorController {

    private final ProductAggregatorService productAggregatorService;

    @GetMapping("/product/{id}")
    public Mono<ResponseEntity<ProductAggregator>> getProduct(@PathVariable int id) {
        return productAggregatorService.productAggregator(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
