package com.adilbou.securityjwt.Repositories;
//
//
//
import com.adilbou.securityjwt.Entities.Besoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//
//import java.util.List;
//
@Repository
public interface BesoinRepository extends JpaRepository<Besoin, Long> {
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


    Besoin findBesoinById(Long id);
//
//    //List<Besoin> findBesoinByvalider();
//
//    List<Besoin> findAllByValiderIsTrue();


    List<Besoin> findByDepartementNameAndIsAffectedIsNullAndIsBesoinInAppelOffreIsNull(String departementName);


    List<Besoin> findByDepartementNameAndIsAffectedIsFalseAndIsBesoinInAppelOffreIsFalse(String departementName);



    List<Besoin> findByDepartementNameAndIsAffectedIsFalseAndIsBesoinInAppelOffreIsFalseAndValiderIsFalse(String departementName);

    List<Besoin> findBesoinByIsAffectedIsFalseAndValiderIsTrue();

    List<Besoin> findBesoinByDepartementNameAndIsAffectedIsFalseAndIsBesoinInAppelOffreIsNullAndValiderIsTrue(String departement);
    List<Besoin> findByDepartementNameAndIsAffectedIsFalseAndIsBesoinInAppelOffreIsFalseAndValiderIsTrue(String departement);

    List<Besoin> findBesoinByDepartementNameAndIsAffectedIsFalseAndIsBesoinInAppelOffreIsFalseAndValiderIsTrue(String departement);


    @Query(value ="SELECT resolution,r.vitesse,COUNT(r.ressource_type) as 'nombre' FROM besoin b, appel_offre app, appel_offre_besoins apb, ressource r, besoin_imprimantes bimp WHERE app.id = apb.appel_offre_id AND apb.besoins_id = b.id AND b.id = bimp.besoin_id AND r.id = bimp.imprimantes_id AND b.valider = 1 AND `is_besoin_in_appel_offre` = 1 AND b.is_affected = 0  GROUP BY r.resolution, r.vitesse", nativeQuery = true)
    List<Object[]> findBesoingroupe();

    @Query(value ="SELECT r.cpu,r.ram,r.stockage,r.ecran,COUNT(r.ressource_type) as 'nombre' FROM besoin b, appel_offre app, appel_offre_besoins apb, ressource r, besoin_ordinateurs bimp WHERE app.id = apb.appel_offre_id AND apb.besoins_id = b.id AND b.id = bimp.besoin_id AND r.id = bimp.ordinateurs_id AND b.valider = 1 AND `is_besoin_in_appel_offre` = 1 AND b.is_affected = 0 GROUP BY r.cpu,r.ram,r.ecran,r.stockage", nativeQuery = true)
    List<Object[]> findBesoingroupeordinateur();
    @Query(value ="SELECT DISTINCT b.id FROM besoin b, appel_offre app, appel_offre_besoins apb, ressource r, besoin_imprimantes bimp WHERE app.id = apb.appel_offre_id AND apb.besoins_id = b.id AND b.id = bimp.besoin_id AND r.id = bimp.imprimantes_id AND b.valider = 1 AND `is_besoin_in_appel_offre` = 1 AND b.is_affected = 0;", nativeQuery = true)
    List<Object[]>  findAllByIsBesoinInAppelOffreIsTrueAndvaliderIsTrueAndIsAffectedIsFalse();

    @Query(value="SELECT apb.besoins_id  FROM appel_offre_besoins apb WHERE appel_offre_id =:idappeloffre", nativeQuery=true)
    List<Long> findallbesoinAppelOffre(@Param("idappeloffre") Long besoinsId);



    @Query(value = "SELECT r.cpu, r.ram, r.stockage, r.ecran, COUNT(r.ressource_type) as nombre " +
            "FROM besoin b, appel_offre app, appel_offre_besoins apb, ressource r, besoin_ordinateurs bimp " +
            "WHERE app.id = apb.appel_offre_id " +
            "AND apb.besoins_id = :besoinsId " +
            "AND :besoinsId = bimp.besoin_id " +
            "AND r.id = bimp.ordinateurs_id " +
            "AND b.valider = 1 " +
            "AND is_besoin_in_appel_offre = 1 " +
            "AND b.is_affected = 0 " +
            "GROUP BY r.cpu, r.ram, r.ecran, r.stockage, r.ram, r.cpu, r.ecran", nativeQuery = true)
    List<Object[]> findBesoingroupeordinateurbyid(@Param("besoinsId") Long besoinsId);

    @Query(value = "SELECT r.resolution, r.vitesse, COUNT(r.ressource_type) as nombre " +
            "FROM besoin b, appel_offre app, appel_offre_besoins apb, ressource r, besoin_imprimantes bimp " +
            "WHERE app.id = apb.appel_offre_id " +
            "AND apb.besoins_id = b.id " +
            "AND b.id = :besoinsId " +
            "AND b.id = bimp.besoin_id " +
            "AND r.id = bimp.imprimantes_id " +
            "AND b.valider = 1 " +
            "AND is_besoin_in_appel_offre = 1 " +
            "AND b.is_affected = 0 " +
            "GROUP BY r.resolution, r.vitesse", nativeQuery = true)
    List<Object[]> findBesoingroupebyid(@Param("besoinsId") Long besoinsId);


    @Query(value ="SELECT resolution,r.vitesse,COUNT(r.ressource_type) as 'nombre' FROM besoin b, appel_offre app, appel_offre_besoins apb, ressource r, besoin_imprimantes bimp WHERE app.id = apb.appel_offre_id AND apb.besoins_id = b.id AND b.id = bimp.besoin_id AND r.id = bimp.imprimantes_id AND b.valider = 1 AND `is_besoin_in_appel_offre` = 1 AND b.is_affected = 0 AND app.id=:idappeloffre GROUP BY r.resolution, r.vitesse", nativeQuery = true)
    List<Object[]> findBesoingroupeimp(@Param("idappeloffre") long idappeloffre);

    @Query(value ="SELECT r.cpu,r.ram,r.stockage,r.ecran,COUNT(r.ressource_type) as 'nombre' FROM besoin b, appel_offre app, appel_offre_besoins apb, ressource r, besoin_ordinateurs bimp WHERE app.id = apb.appel_offre_id AND apb.besoins_id = b.id AND b.id = bimp.besoin_id AND r.id = bimp.ordinateurs_id AND b.valider = 1 AND `is_besoin_in_appel_offre` = 1 AND b.is_affected = 0 AND app.id=:idappeloffre GROUP BY r.cpu,r.ram,r.ecran,r.stockage", nativeQuery = true)
    List<Object[]> findBesoingroupeordinateurord(@Param("idappeloffre") long idappeloffre);
}

