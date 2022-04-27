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

/** 이 어노테이션(@Configuration)을 제거하면 CGLIB 기술 없이 순수한 AppConfig로 스프링 빈에 등록된 것을 확인 가능함.
 * 즉 이 AppConfig에선  MemberRepository가 총 3번 호출된 것을 볼 수 있다.
 * CGLIB 기술은 이를 방지해 싱글톤을 보장하게 한다.
 *
 * CGLIB의 대략적 동작: @Bean이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고,
 * 스프링 빈이 없으면 생성해서 스프링 빈으로
 * 등록하고 반환하는 코드가 동적으로 만들어진다.*/
@Configuration
/** 애플리케이션 구성 설정 정보를 뜻함 **/
public class AppConfig {

    /**
     * @Bean memberService -> new MemoryMemberRepository()
     * @Bean orderService -> new MemoryMemberRepository()
     * 아니 이렇게 되면 싱글톤이 깨지는 거 아닌가?
     */

    @Bean
    /** Bean 어노테이션을 통해 스프링 컨테이너에 등록됨 **/
    public MemberService memberService() { /** 생성자 주입 **/
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository()); // 컨트롤 + alt + M 누르면 중복된 걸 extract method로 만들어준다.
    }

    @Bean
    public OrderService orderService() { /** 생성자 주입 **/
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    /**
     * 역할에 따른 구현 클래스 start
     * 이제 구현체를 변경할 때 아래의 메서드들만 수정하면 된다.
     **/
    @Bean
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    /** 역할에 따른 구현 클래스 end **/
}
