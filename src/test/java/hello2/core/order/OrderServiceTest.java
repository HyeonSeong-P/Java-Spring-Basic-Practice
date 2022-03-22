package hello2.core.order;

import hello2.core.AppConfig;
import hello2.core.member.Grade;
import hello2.core.member.Member;
import hello2.core.member.MemberService;
import hello2.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;

    @BeforeEach // 테스트 전에 실행되는 코드
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    /**
     * 멤버서비스테스트와 여기서 볼 수 있듯이 이렇게 단위 테스트(스프링이나 컨테이너의 도움없이 순수한 자바코드로 테스트 하는 것)를 하는 게 중요하다
     */
    @Test
    void createOrder(){
        Long memberId = 1L;
        Member member = new Member(memberId,"memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId,"itemA",10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
