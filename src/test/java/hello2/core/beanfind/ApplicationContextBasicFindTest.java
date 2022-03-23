package hello2.core.beanfind;

import hello2.core.AppConfig;
import hello2.core.member.MemberService;
import hello2.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService",MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }


    // 객체가 스프링 빈에 등록이 되어있으면 이렇게도 조회 가능하다.
    // 그러나 이전에도 배웠듯이 역할과 구현을 구분해야 하는데 여긴 구현에 의존하는 코드라 인터페이스로 조회하는게 좋다
    // but, 살다보면 이런저런 경우가 있기 때문에 알아두면 좋다
    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2(){
        MemberService memberService = ac.getBean("memberService",MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX(){
        //MemberService memberService = ac.getBean("XXXXX",MemberServiceImpl.class);

        /** 오른쪽에 람다를 실행했을 때 왼쪽의 예외가 throw 되어야 성공 **/
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("XXXXX",MemberServiceImpl.class));
    }
}
