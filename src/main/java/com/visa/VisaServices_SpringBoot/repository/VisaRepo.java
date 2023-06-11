package com.visa.VisaServices_SpringBoot.repository;


import com.visa.VisaServices_SpringBoot.models.VisaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisaRepo extends JpaRepository<VisaModel, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM VisaModel where sub_cate = ?")
    public VisaModel searchVisa(String subCategory);
}
