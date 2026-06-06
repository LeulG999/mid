package edu.miu.repository;

import edu.miu.model.Member;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryMemberRepository implements MemberRepository {
    private final Map<String, Member> membersById = new LinkedHashMap<String, Member>();

    public Optional<Member> findById(String memberId) {
        return Optional.ofNullable(membersById.get(memberId));
    }

    public List<Member> findAll() {
        return new ArrayList<Member>(membersById.values());
    }

    public void save(Member member) {
        if (member == null) {
            throw new IllegalArgumentException("member is required");
        }
        membersById.put(member.getMemberId(), member);
    }
}
