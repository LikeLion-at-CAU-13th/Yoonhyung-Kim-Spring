package com.example.likelion13spring.repository;

import com.example.likelion13spring.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
//public class MemberRepository {
//    @PersistenceContext
//    private EntityManager em;
//
//    public void save(Member member) {
//        em.persist(member);
//    }
//
//    public Optional<Member> findById(Long id){
//        Member member = em.find(Member.class, id);
//        return Optional.ofNullable(member);
//    }
//
//    public List<Member> findAll(){
//        return em.createQuery("SELECT m FROM Member m", Member.class).getResultList();
//    }
//
//    public Optional<Member> findByEmail(String email){
//        List<Member> result = em.createQuery("SELECT m FROM Member m WHERE m.email = :email", Member.class)
//                .setParameter("email",email)
//                .getResultList();
//        return result.stream().findFirst();
//    }
//
//    public List<Member> findByName(String name){
//        return em.createQuery("SELECT m FROM member m WHERE m.name = :name",Member.class)
//                .setParameter("name", name)
//                .getResultList();
//    }
//
//    public void delete(Member member){
//        em.remove(member);
//    }
//}

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByName(String name);
    Optional<Member> findByEmail(String email);

    Page<Member> findByAgeGreaterThanEqual(int age, Pageable pageable);

    Page<Member> findByNameStartingWith(String name, Pageable pageable);

    // 이름 중복 검사 쿼리
    boolean existsByName(String name);

}
