package br.com.takeaguide.takeaguide.repositories.mysql;

import br.com.takeaguide.takeaguide.entities.Ad;
import br.com.takeaguide.takeaguide.dtos.ad.AdDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.Instant;
import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Ad (user_id, ad_content) VALUES (:id, :ad)", nativeQuery = true)
    BigInteger createAd(@Param("id") String id, @Param("ad") byte[] ad);

    @Query("SELECT new br.com.takeaguide.takeaguide.dtos.ad.AdDto(a.id, a.user.cpf, a.adContent) " +
            "FROM Ad a WHERE a.user.cpf = :userId AND a.deletedAt IS NULL")
    List<AdDto> retrieveAdsByUserId(@Param("userId") String userId);

    @Query("SELECT new br.com.takeaguide.takeaguide.dtos.ad.AdDto(a.id, a.user.cpf, a.adContent) " +
            "FROM Ad a WHERE a.id = :id AND a.deletedAt IS NULL")
    List<AdDto> retrieveAdsById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Ad a SET a.adContent = :adContent WHERE a.id = :id RETURNING a.id", nativeQuery = true)
    BigInteger updateAdContent(@Param("id") Long id, @Param("adContent") byte[] adContent);

    @Transactional
    @Modifying
    @Query("UPDATE Ad a SET a.deletedAt = UTC_TIMESTAMP() WHERE a.id = :id")
    void softDeleteAd(@Param("id") Long id);
}