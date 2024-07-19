package com.shipshoe.userservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "certification")
@Table(name = "certification")
public class CertificationEntity {

    @Id
    private String email;
    private String certificationNumber;

    public CertificationEntity(String email, String certificationNumber) {
        this.email = email;
        this.certificationNumber = certificationNumber;
    }
}
