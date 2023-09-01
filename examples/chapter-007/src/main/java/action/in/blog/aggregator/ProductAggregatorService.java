package action.in.blog.aggregator;

import action.in.blog.client.ProductClient;
import action.in.blog.client.PromotionClient;
import action.in.blog.client.ReviewClient;
import action.in.blog.domain.ProductAggregator;
import action.in.blog.domain.ProductResponse;
import action.in.blog.domain.PromotionResponse;
import action.in.blog.domain.Review;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;

import java.util.List;

@Service
public class ProductAggregatorService {

    private final ProductClient productClient;
    private final PromotionClient promotionClient;
    private final ReviewClient reviewClient;

    public ProductAggregatorService(
            ProductClient productClient,
            PromotionClient promotionClient,
            ReviewClient reviewClient
    ) {
        this.productClient = productClient;
        this.promotionClient = promotionClient;
        this.reviewClient = reviewClient;
    }

    private static ProductAggregator apply(Tuple3<ProductResponse, PromotionResponse, List<Review>> tuple) {
        return ProductAggregator.of(tuple.getT1(), tuple.getT2(), tuple.getT3());
    }

    public Mono<ProductAggregator> productAggregator(int id) {
        return Mono.zip(
                productClient.getProduct(id),
                promotionClient.getPromotion(id),
                reviewClient.getReviews(id)
        ).mapNotNull(ProductAggregatorService::apply);
    }
}
