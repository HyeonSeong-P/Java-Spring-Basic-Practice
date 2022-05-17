package hello2.core.order;

import hello2.core.annotation.MainDiscountPolicy;
import hello2.core.discount.DiscountPolicy;
import hello2.core.discount.FixDiscountPolicy;
import hello2.core.member.Member;
import hello2.core.member.MemberRepository;
import hello2.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor /**  (lombok 어노테이션)클래스 안에 final이 붙은 field를 보고 그것들을 args로 하는 생성자를 자동으로 만들어준다.*/
public class OrderServiceImpl implements OrderService {

    /**
     * 필드 주입: 외부에서 변경이 불가능해서 테스트 하기 힘들다는 치명적인 단점이 있다.
     * 또한 DI 프레임워크가 없으면 아무것도 할 수 없다. 즉 실제로 사용하진 말자!
     * 다만
     * 1. 애플리케이션의 실제 코드와 관계 없는 테스트 코드
     * 2. 스프링 설정을 목적으로 하는 @Configuration 같은 곳에서만 특별한 용도로 사용
     * 이 같은 경우엔 사용해도 무방하다. ( 그래도 되도록이면 1번 경우에만 쓰도록 하고 나머진 쓰지 말자. )
     */

    /** 수정자 주입(변경이 필요할 때 사용, 변경 시엔 강제로 불러오면 됨.
     private MemberRepository memberRepository;
    @Autowired(required = false) // 주입할 대상이 없어도 동작하게 하려면 required 옵션을 false로 주면 됨
    public void setMemberRepository(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }*/

    private final MemberRepository memberRepository;

    /** (@Autowired 필드 명 매칭) 이런식으로 필드 이름이나 파라미터 이름으로 빈 이름을 추가 매칭 가능한데
     * 이걸로 똑같은 타입의 스프링 빈이 2개 있을 때(ex. 해당 프로젝트의 경우 rate/fix DiscountPolicy) 문제를 해결할 수 있다.
     * 당연하지만 appconfig xml파일도 수정해줘야 한다.
     * private final DiscountPolicy rateDiscountPolicy;*/

    /** 똑같은 타입의 스프링 빈이 2개 있을 때 문제 해결법, 컴파일 시 타입 체크가 가능한 애노테이션을 직접 만드는 방법 */
    @MainDiscountPolicy private final DiscountPolicy discountPolicy;


    /** lombok 안 쓸때 autowired 이용한 생성자 주입 법.
    @Autowired //이게 생성자 주입 , 생성자가 딱 1개만 있으면 @Autowired 생략 가능.
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/

    /**
     * 일반 메서드 주입: 일반 메서드를 통해 주입 받을 수 있음.
     * 한번에 여러 필드를 주입 받을 수 있지만 __일반적으로 잘 사용하지 않는다.__
    @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy){
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }**/


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


    // 싱글톤 깨지는지 확인하기 위한 테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
