package com.panda.JSON_EX.JSON_EX.Member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // Derived Query Method : 메소드 이름으로 SQL 쿼리 생성
    // findBy컬럼명(컬럼값) : 컬럼값으로 데이터 조회
    boolean existsByUsername(String username);
    Optional<Member> findByUsername(String username);
}
