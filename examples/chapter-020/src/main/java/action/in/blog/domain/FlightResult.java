package action.in.blog.domain;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@ToString
public class FlightResult {
    private String airline;
    private String from;
    private String to;
    private BigDecimal price;
    private LocalDate date;
}
