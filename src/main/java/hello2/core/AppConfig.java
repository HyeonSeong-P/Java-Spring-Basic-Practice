package hello2.core;

import hello2.core.discount.DiscountPolicy;
import hello2.core.discount.FixDiscountPolicy;
import hello2.core.discount.RateDiscountPolicy;
import hello2.core.member.MemberService;
import hello2.core.member.MemberServiceImpl;
import hello2.core.member.MemoryMemberRepository;
import hello2.core.order.OrderService;
import hello2.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration /** 애플리케이션 구성 설정 정보를 뜻함 **/
public class AppConfig {

    @Bean /** Bean 어노테이션을 통해 스프링 컨테이너에 등록됨 **/
    public MemberService memberService(){ /** 생성자 주입 **/
        return new MemberServiceImpl(memberRepository()); // 컨트롤 + alt + M 누르면 중복된 걸 extract method로 만들어준다.
    }

    @Bean
    public OrderService orderService(){ /** 생성자 주입 **/
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    /** 역할에 따른 구현 클래스 start
     * 이제 구현체를 변경할 때 아래의 메서드들만 수정하면 된다.**/
    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
    @Bean
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    /** 역할에 따른 구현 클래스 end **/
}
