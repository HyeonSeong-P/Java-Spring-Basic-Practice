package hello2.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** 관례상 인터페이스에 대한 구현체가 하나만 있으면 ~~Impl 이런식으로 명명한다. **/
@Component
public class MemberServiceImpl implements MemberService{

    /** 실제로 바로 밑에 코드는 우항에서 볼 수 있듯이 구현체 코드에 의존한다. 좋지 않음.
    private final MemberRepository memberRepository = new MemoryMemberRepository(); // 추천 클래스 그런 거 뜬 상태에서 컨트롤+쉬프트+엔터 누르면 세미클론까지 입력해줌
    */
    private final MemberRepository memberRepository;

    /**
     *  @Autowired: 컴포넌트 스캔으로 빈이 자동으로 등록될 때 의존성 주입을 위한 어노테이션으로 생성자에 붙여준다.
     */
    @Autowired // ac.getBean(MemberRepository.class) <- 이 코드가 자동으로 들어간다고 보면 된다.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 싱글톤 깨지는지 확인하기 위한 테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
