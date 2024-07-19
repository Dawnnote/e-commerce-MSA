package com.shipshoe.userservice.repository;

import com.shipshoe.userservice.entity.CertificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CertificationRepository extends JpaRepository<CertificationEntity, Long> {


    CertificationEntity findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);
}
