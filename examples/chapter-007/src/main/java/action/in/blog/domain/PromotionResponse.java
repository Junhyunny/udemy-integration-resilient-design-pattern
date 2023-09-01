package action.in.blog.domain;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@ToString
public class PromotionResponse {
    private int id;
    private String type;
    private BigDecimal discount;
    private LocalDate endDate;

    public static PromotionResponse noPromotion() {
        return PromotionResponse.builder()
                .id(-1)
                .type("No Promotion")
                .discount(BigDecimal.ZERO)
                .endDate(LocalDate.now())
                .build();
    }
}
