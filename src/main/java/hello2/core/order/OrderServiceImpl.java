package hello2.core.order;

import hello2.core.discount.DiscountPolicy;
import hello2.core.discount.FixDiscountPolicy;
import hello2.core.member.Member;
import hello2.core.member.MemberRepository;
import hello2.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {



    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    /**
     * 이런 식으로 할인 정책을 바꾸려면 클라이언트인 OrderServiceImpl의 코드를 바꿔야 한다.
     * 이순간 OCP 위반, 자세한 건 자료 26페이지 참고
     * //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
     * //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
     * */



    /**
     * SRP,단일 책임 원칙이 잘 지켜진 경우임
     * 왜냐, 지금 할인 정책 관련 객체를 가짐으로써 주문은 할인 정책에 대해 모르고 객체만 사용할 뿐
     * 할인 정책이 바뀌거나 그런 건 신경을 쓰지 않아도 됨! 그건 할인 정책 쪽에서 할 일!
     */
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
