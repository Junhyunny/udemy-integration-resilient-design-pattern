package action.in.blog.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductResponse {
    private int id;
    private String category;
    private String description;
    private int price;
}
