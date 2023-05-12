package com.adilbou.securityjwt.Repositories;

import com.adilbou.securityjwt.Entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CanalMessagerieRepository extends JpaRepository<Message, Long> {
    List<Message> findByTypeMsg(String type);
}
