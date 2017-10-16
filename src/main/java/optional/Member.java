package optional;

import lombok.Data;

@Data
public class Member {
    private Long id;
    private String name;
    private Address address;

    public Member() {}

    public Member(String name) {
        this.name = name;
    }
}