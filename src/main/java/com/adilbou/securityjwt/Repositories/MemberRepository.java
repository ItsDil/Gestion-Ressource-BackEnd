package com.adilbou.securityjwt.Repositories;

import com.adilbou.securityjwt.Entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Integer> {
    Member findMembreDepartementById(Integer id);


}
