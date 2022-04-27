package hello2.core.sigletone;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        /**
         * A는 10000원을 주문했는데
         * B가 주문을 해서
         * 서비스의 필드 값이 B걸로 바뀌어버림.
         * 그래서 A가 자기 주문을 조회했는데 B의 주문 조회가 떠버리게 된다.
         * 강의에선 복잡해서 스레드는 직접 사용하지 않았는데 그 상황을 간단하게 구현한 것
         */
        //ThreadA: A사용자 10000원 주문
        /**statefulService1.order("userA",10000);// 무상태 설계 이전 버전 */
        int userAPrice = statefulService1.order("userA", 10000);
        //ThreadB: B사용자 20000원 주문
        /** statefulService2.order("userB",20000);// 무상태 설계 이전 버전*/
        int userBPrice = statefulService2.order("userB", 20000);

        //ThreadA: 사용자A 주문 금액 조회
        //int price = statefulService1.getPrice();
        System.out.println("price = " + userAPrice);

        Assertions.assertThat(userAPrice).isEqualTo(10000);
    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}
