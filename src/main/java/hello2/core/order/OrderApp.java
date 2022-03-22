package hello2.core.order;

import hello2.core.AppConfig;
import hello2.core.member.Grade;
import hello2.core.member.Member;
import hello2.core.member.MemberService;
import hello2.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
       /* AppConfig appConfig = new AppConfig(); 스프링 컨테이너 사용 전 DI
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();*/

        /**
         * 스프링 컨테이너를 통한 DI
         */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        /* DI 설계전
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl(); */

        Long memberId = 1L;
        Member member = new Member(memberId,"memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId,"itemA",20000);

        System.out.println("order = " + order);
        System.out.println("order calculatePrice = " + order.calculatePrice());
    }
}
