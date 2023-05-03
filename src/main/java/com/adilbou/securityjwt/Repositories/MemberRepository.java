package com.adilbou.securityjwt.Repositories;

import com.adilbou.securityjwt.Entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Integer> {
    Member findMembreDepartementById(Integer id);
    List<Member> findMembreDepartementBydepartement(String departementName);

}
