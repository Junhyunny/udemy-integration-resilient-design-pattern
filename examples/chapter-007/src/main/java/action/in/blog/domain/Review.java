package action.in.blog.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Review {
    private int id;
    private String user;
    private int rating;
    private String comment;
}
