package action.in.blog.domain;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@ToString
public class Price {
    private int listPrice;
    private BigDecimal discount;
    private BigDecimal discountedPrice;
    private BigDecimal amountSaved;
    private LocalDate endDate;

    public void calculate() {
        var hundred = BigDecimal.valueOf(100);
        var price = BigDecimal.valueOf(listPrice);
        amountSaved = discount.multiply(price).divide(hundred);
        discountedPrice = price.subtract(amountSaved);
    }
}
