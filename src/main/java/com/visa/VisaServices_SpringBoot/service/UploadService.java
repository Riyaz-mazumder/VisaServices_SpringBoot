package com.visa.VisaServices_SpringBoot.service;

import com.visa.VisaServices_SpringBoot.models.UploadModel;
import com.visa.VisaServices_SpringBoot.repository.UploadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UploadService {

    @Autowired
    private UploadRepo uploadRepo;

    public List<UploadModel> getAllUploadedFile(){
        return uploadRepo.findAll();
    }

    public void saveUploadedFile(UploadModel uploadModel){
        uploadRepo.save(uploadModel);
    }

    public void deleteUploadedFile(Long id){
        uploadRepo.deleteById(id);
    }

    public UploadModel findUploadedFileById(Long id){
      return uploadRepo.findById(id).orElse(new UploadModel());
    }
}
