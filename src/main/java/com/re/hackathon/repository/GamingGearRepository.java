package com.re.hackathon.repository;

import com.re.hackathon.entity.GamingGear;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GamingGearRepository extends JpaRepository<GamingGear, Long> {

    @Query("SELECT g FROM GamingGear g WHERE g.isDelete = false AND " +
           "(:productName IS NULL OR LOWER(g.productName) LIKE LOWER(CONCAT('%', :productName, '%'))) AND " +
           "(:serialCode IS NULL OR LOWER(g.serialCode) LIKE LOWER(CONCAT('%', :serialCode, '%')))")
    Page<GamingGear> searchActive(@Param("productName") String productName,
                                  @Param("serialCode") String serialCode,
                                  Pageable pageable);

    Page<GamingGear> findByIsDeleteFalse(Pageable pageable);
}
