package com.chaduvuko.V1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chaduvuko.V1.model.Certificate;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Integer> {

}
