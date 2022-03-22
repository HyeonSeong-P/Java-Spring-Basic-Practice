package hello2.core.member;

/** 관례상 인터페이스에 대한 구현체가 하나만 있으면 ~~Impl 이런식으로 명명한다. **/
public class MemberServiceImpl implements MemberService{

    /** 실제로 바로 밑에 코드는 우항에서 볼 수 있듯이 구현체 코드에 의존한다. 좋지 않음.
    private final MemberRepository memberRepository = new MemoryMemberRepository(); // 추천 클래스 그런 거 뜬 상태에서 컨트롤+쉬프트+엔터 누르면 세미클론까지 입력해줌
    */
    private final MemberRepository memberRepository;

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
}
