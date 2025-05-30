package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional
@Transactional

public class MemberService {}
@Service

public class MemberService {
    private final MemberRepository memberRepository;
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    @Autowired
    public MemberService(MemoryMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        long start = System.currentTimeMillis();
        try {
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
        }
        finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join " + timeMs + "ms");
        }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

        /**
         *
         전체 회원 조회
         */
        public List<Member> findMembers() {
            long start = System.currentTimeMillis();
            try {
                return memberRepository.findAll();
            }
            finally {
                long finish = System.currentTimeMillis();
                long timeMs = finish - start;
                System.out.println("findMembers " + timeMs + "ms");
            }
        }
    }

