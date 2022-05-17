package hello2.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository{

    /** 여러군데에서 접근할 수 있어 동시성 문제때문에 concurrent hashmap을 쓰는 게 바람직함.
     * 하지만 강의에선 이것도 설명해야 해서 일단 생략. 내가 알아보자!
     **/
    private static Map<Long, Member> store = new HashMap<>();

    // 에러 처리까지 하면 예제가 복잡해지니 강의에선 생략!
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
