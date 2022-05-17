package hello2.core.discount;

import hello2.core.annotation.MainDiscountPolicy;
import hello2.core.member.Grade;
import hello2.core.member.Member;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy{
    private int discountParent = 10;
    @Override
    public int discount(Member member, int price) { // 컨트롤 + 쉬프트 + T 하면 테스트 생성 가능
        if(member.getGrade() == Grade.VIP){
            return price * discountParent / 100;
        }
        else{
            return 0;
        }
    }
}
