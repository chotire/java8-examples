package optional;

import java.util.Date;

import lombok.Data;

@Data
public class Order {
    private Long id;
    private Date date;
    private Member member;

    public Order() {}

    public Order(Long id, Member member) {
        this.id = id;
        this.member = member;
    }
}