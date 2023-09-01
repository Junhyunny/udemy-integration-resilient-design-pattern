package action.in.blog.domain;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Builder
@Data
@ToString
public class ProductAggregator {
    private int id;
    private String category;
    private String description;
    private String type;
    private Price price;
    private List<Review> reviews;

    public static ProductAggregator of(ProductResponse product, PromotionResponse promotion, List<Review> reviews) {
        var price = Price.builder()
                .listPrice(product.getPrice())
                .discount(promotion.getDiscount())
                .endDate(promotion.getEndDate())
                .build();
        price.calculate();
        return ProductAggregator.builder()
                .id(product.getId())
                .category(product.getCategory())
                .description(product.getDescription())
                .type(promotion.getType())
                .price(price)
                .reviews(reviews)
                .build();
    }
}
