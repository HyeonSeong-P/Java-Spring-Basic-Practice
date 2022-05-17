package hello2.core.autowired;

import hello2.core.AutoAppConfig;
import hello2.core.discount.DiscountPolicy;
import hello2.core.member.Grade;
import hello2.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest {
    @Test
    void findAllBean(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(member,10000,"fixDiscountPolicy");
        Assertions.assertThat(discountService).isInstanceOf(DiscountService.class);
        Assertions.assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        Assertions.assertThat(rateDiscountPrice).isEqualTo(2000);

    }


    static class DiscountService{
        /**
         * Map은 map의 키에 스프링 빈의 이름을 넣어주고, 그 값으로 이 예제에선 DiscountPolicy 타입으로 조회한 모든 스프링 빈을 담아준다.
         * List는 이 예제에선 DiscountPolicy 타입으로 조회한 모든 스프링 빈을 담아준다.
         * 해당하는 타입의 스프링 빈이 없으면, 빈 컬렉션이나 Map을 주입한다.
         * 이런 식으로 전략 패턴(이 예제는 다형성 패턴)을 쉽게 구현할 수 있다.(discount 메서드 참고)
          */
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String,DiscountPolicy> policyMap, List<DiscountPolicy> policies){
            this.policies = policies;
            this.policyMap = policyMap;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member,price);
        }
    }
}
