package com.adilbou.securityjwt.Repositories;

import com.adilbou.securityjwt.Entities.User;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);
    List<User> findAll();



//    @Query("SELECT e FROM User e WHERE e. = :type")
//    List<User> findAllByType(String type);
//}

}
