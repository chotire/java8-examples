package optional;

import java.util.Optional;

public class OptionalExample {
    /**
     * 1. �������� NPE ��� ����
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
     * 2. Optional�� ����� Null safe�� ����
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
        // ����ִ� Optional ��ü�� ����
        Optional<Member> member1 = Optional.empty();
        // null�� �ƴ� ��ü�� ��� �ִ� Optional ��ü�� ����
        // null�� �Ѿ�� ���, NPE�� ������ ������ ����
        // Optional<Member> member2 = Optional.of(null);
        Optional<Member> member2 = Optional.of(new Member());
        // null���� �ƴ��� Ȯ���� �� ���� ��ü�� ��� �ִ� Optional ��ü�� ����
        // null�� �Ѿ�� ���, NPE�� ������ �ʰ� Optional.empty()�� �����ϰ� ��� �ִ� Optional ��ü�� ����
        Optional<Address> address = Optional.ofNullable(null);

        /*
         * Optional�� ��� �ִ� ��ü �����ϱ�
         *
         * get(): ����ִ� Optional ��ü�� ���ؼ�, NoSuchElementException�� ������.
         * orElse(T other): ����ִ� Optional ��ü�� ���ؼ�, �Ѿ�� ���ڸ� ��ȯ
         * orElseGet(Supplier<? extends T> other): ����ִ� Optional ��ü�� ���ؼ�, �Ѿ�� �Լ��� ���ڸ� ���� ������ ��ü�� ��ȯ
         * orElseThrow(Supplier<? extends X> exceptionSupplier): ����ִ� Optional ��ü�� ���ؼ�, �Ѿ�� �Լ��� ���ڸ� ���� ������ ���ܸ� ������.
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