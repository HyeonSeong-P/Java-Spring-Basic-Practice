package hello2.core.discount;

import hello2.core.member.Grade;
import hello2.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
/*@Qualifier("mainDiscountPolicy") // (@Qualifier 사용) 완전히 똑같은 타입의 스프링 빈이 2개 있을 때 해결법
 //이걸 토대로 주입 시 @Qualifier("mainDiscountPolicy) <- 요런식으로 필드나 파라미터 앞에 붙여주면 됨.
 다만 퀄리파이어는 퀄리파이어를 찾는 용도로만 사용하는게 명확하고 좋다.**/
@Primary /**(@Primary 사용) 완전히 똑같은 타입의 스프링 빈이 2개 있을 때 해결법, 이것만 붙여주면 이걸로 빈이 매칭된다*/
public class FixDiscountPolicy implements DiscountPolicy{
    private  int discountFixAmount = 1000; // 1000원 할인
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return discountFixAmount;
        }else{
            return 0;
        }
    }
}
