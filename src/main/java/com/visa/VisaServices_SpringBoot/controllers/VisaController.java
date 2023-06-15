package com.visa.VisaServices_SpringBoot.controllers;


import com.visa.VisaServices_SpringBoot.models.VisaModel;
import com.visa.VisaServices_SpringBoot.service.QRCodeService;
import com.visa.VisaServices_SpringBoot.service.VisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class VisaController {

    @Autowired
    private VisaService visaService;

    @Autowired
    private QRCodeService qrCodeService;


    @GetMapping("visa")
    public List<VisaModel> getAllVisa() {
        return visaService.getAllVisa_();
    }

    @GetMapping("visa/{id}")
    public VisaModel getVisaById(@PathVariable Long id){
        return visaService.getVisaById_(id);
    }

    @PostMapping("visa")
    public void saveVisa(@RequestBody VisaModel visaModel) {
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


    @GetMapping("qrCode/{path}")
    public byte[] getQrCode(@PathVariable String path) throws Exception{
        return qrCodeService.generateQRCodeWithLogo(path, "https://live.staticflickr.com/65535/52974974959_4726f09725_m.jpg");
    }


}
