package com.visa.VisaServices_SpringBoot.repository;


import com.visa.VisaServices_SpringBoot.models.VisaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisaRepo extends JpaRepository<VisaModel, Long> {
}
