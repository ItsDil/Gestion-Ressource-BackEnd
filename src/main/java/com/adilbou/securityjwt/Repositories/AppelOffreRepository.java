package com.adilbou.securityjwt.Repositories;

import com.adilbou.securityjwt.Entities.AppelOffre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppelOffreRepository extends JpaRepository<AppelOffre, Long> {
    AppelOffre findAppelOffreById(Long id);
    List<AppelOffre> findAppelOffreByDatePubIsNotNull();
}
