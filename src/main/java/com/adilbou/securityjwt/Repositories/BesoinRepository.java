//package com.adilbou.securityjwt.Repositories;
//
//
//
//import com.adilbou.securityjwt.Entities.Besoin;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface BesoinRepository extends JpaRepository<Besoin, Long> {
//
//    List<Besoin> findBesoinByIdMembreDepartementAndIsAffectedIsFalse(Integer id);
//    List<Besoin> findBesoinByDepartementName(String nameDepartement);
//    List<Besoin> findBesoinByIdMembreDepartement(Integer id);
//    Besoin findBesoinByIdMembreDepartementAndIsBesoinInAppelOffreIsFalse(Integer id);
//    List<Besoin> findBesoinByDepartementNameAndIsBesoinInAppelOffreIsFalse(String nameDepartemeent);
//    List<Besoin> findAllByIsBesoinInAppelOffreIsFalse();
//
//    @Query(value = "SELECT * FROM besoin WHERE is_besoin_in_appel_offre IS NULL", nativeQuery = true)
//    List<Besoin> findBesoinsNotInAppelOffre();
//
//
//    Besoin findBesoinById(Long id);
//
//    //List<Besoin> findBesoinByvalider();
//
//    List<Besoin> findAllByValiderIsTrue();
//}
