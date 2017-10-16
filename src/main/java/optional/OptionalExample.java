package optional;

import java.util.Optional;

public class OptionalExample {
    /**
     * 1. 전통적인 NPE 방어 패턴
     */
    public String getCityOfMemberFromOrderByTraditional(Order order) {
        if (order != null) {
            Member member = order.getMember();
            if (member != null) {
                Address address = member.getAddress();
                if (address != null) {
                    String city = address.getCity();
                    if (city != null) {
                        return city;
                    }
                }
            }
        }
        return "Seoul"; // default
    }

    /**
     * 2. Optional을 사용한 Null safe한 패턴
     */
    public String getCityOfMemberFromOrderByOptional(Order order) {
        return Optional.ofNullable(order)
                .map(Order::getMember)
                .map(Member::getAddress)
                .map(Address::getCity)
                .orElse("Seoul");
    }

    public Optional<Member> getMemberIfOrderId(Order order) {
        return Optional.ofNullable(order)
                .filter(o -> o.getId() == 2L)
                .map(Order::getMember);
    }

    @SuppressWarnings("unused")
    public void create() {
        // 비어있는 Optional 객체를 생성
        Optional<Member> member1 = Optional.empty();
        // null이 아닌 객체를 담고 있는 Optional 객체를 생성
        // null이 넘어올 경우, NPE를 던지기 때문에 주의
        // Optional<Member> member2 = Optional.of(null);
        Optional<Member> member2 = Optional.of(new Member());
        // null인지 아닌지 확신할 수 없는 객체를 담고 있는 Optional 객체를 생성
        // null이 넘어올 경우, NPE를 던지지 않고 Optional.empty()와 동일하게 비어 있는 Optional 객체를 생성
        Optional<Address> address = Optional.ofNullable(null);

        /*
         * Optional이 담고 있는 객체 접근하기
         *
         * get(): 비어있는 Optional 객체에 대해서, NoSuchElementException을 던진다.
         * orElse(T other): 비어있는 Optional 객체에 대해서, 넘어온 인자를 반환
         * orElseGet(Supplier<? extends T> other): 비어있는 Optional 객체에 대해서, 넘어온 함수형 인자를 통해 생성된 객체를 반환
         * orElseThrow(Supplier<? extends X> exceptionSupplier): 비어있는 Optional 객체에 대해서, 넘어온 함수형 인자를 통해 생성된 예외를 던진다.
         */
    }

    public static void main(String[] args) {
        OptionalExample example = new OptionalExample();
        example.create();
        System.out.println(example.getCityOfMemberFromOrderByTraditional(null));
        System.out.println(example.getCityOfMemberFromOrderByOptional(null));

        Order order1 = new Order(1L, new Member("choyongsang"));
        Order order2 = new Order(2L, new Member("chotire"));
        System.out.println(example.getMemberIfOrderId(order1));
        System.out.println(example.getMemberIfOrderId(order2));
    }
}