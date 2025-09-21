package com.example.likelion13spring.service;

import com.example.likelion13spring.domain.Member;
import com.example.likelion13spring.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Page<Member> getMembersByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return memberRepository.findAll(pageable);
    }

    public Page<Member> getAdultMembersSortedByName(int age, int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return memberRepository.findByAgeGreaterThanEqual(age,pageable);
    }

    public Page<Member> getMembersByNamePrefix(String name, int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        return memberRepository.findByNameStartingWith(name, pageable);
    }

}
