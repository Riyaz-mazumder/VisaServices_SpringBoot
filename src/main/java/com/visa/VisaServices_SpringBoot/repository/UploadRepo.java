package com.visa.VisaServices_SpringBoot.repository;

import com.visa.VisaServices_SpringBoot.models.UploadModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadRepo extends JpaRepository<UploadModel, Long> {

}
