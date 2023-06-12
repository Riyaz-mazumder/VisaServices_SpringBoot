package com.visa.VisaServices_SpringBoot.controllers;


import com.visa.VisaServices_SpringBoot.models.VisaModel;
import com.visa.VisaServices_SpringBoot.service.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class VisaController {

    @Autowired
    private VisaService visaService;


    @GetMapping("visa")
    public List<VisaModel> getAllVisa() {
        return visaService.getAllVisa_();
    }

    @GetMapping("visa/{id}")
    public VisaModel getVisaById(@PathVariable Long id){
        return visaService.getVisaById_(id);
    }

    @PostMapping("visa")
    public void saveVisa(@RequestBody VisaModel visaModel){
        visaService.saveVisa(visaModel);
    }

    @PutMapping("visa")
    public void updateVisa(@RequestBody VisaModel visaModel){
        visaService.saveVisa(visaModel);
    }

    @DeleteMapping("visa/{id}")
    public void deleteVisa(@PathVariable Long id){
        visaService.deleteVisa(id);
    }



    @GetMapping("visa/{holderPassportNumber}/{holderDateOfBirth}/{holderNationality}")
    public List<VisaModel> searchVis(@PathVariable String holderPassportNumber, @PathVariable String holderDateOfBirth, @PathVariable String holderNationality){
        return visaService.searchVisa_(holderPassportNumber, holderDateOfBirth, holderNationality);
    }

}
