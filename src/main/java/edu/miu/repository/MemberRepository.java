package edu.miu.repository;

import edu.miu.model.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Optional<Member> findById(String memberId);

    List<Member> findAll();

    void save(Member member);
}
