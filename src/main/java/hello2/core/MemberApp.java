package hello2.core;

import hello2.core.member.Grade;
import hello2.core.member.Member;
import hello2.core.member.MemberService;
import hello2.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        /*AppConfig appConfig = new AppConfig(); // 스프링 컨테이너 이전 DI 코드
        MemberService memberService = appConfig.memberService();*/

        /** ApplicationContext를 스프링 컨테이너라 한다. 생성자에 파라미터로 만든 AppConfig 클래스를 넣어주면
         * 안에 설정 정보를 보고 스프링 컨테이너에 넣어준다 **/
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        /** AppConfig 안의 메서드 이름을 보고 객체를 찾아온다.**/
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        //MemberService memberService = new MemberServiceImpl(); DI 설계 전 코드
        Member member = new Member(1L, "memberA", Grade.VIP); // 우항만 작성해놓고 컨트롤+alt+v 하면 좌항 자동 생성
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }

}
