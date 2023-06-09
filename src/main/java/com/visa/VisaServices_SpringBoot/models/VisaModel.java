package com.visa.VisaServices_SpringBoot.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class VisaModel {
    @Id
    @GeneratedValue
    private Long id;


    private int visaNumber;
    private String visaTypeInArabic;
    private String visaType;
    private String visaPurposeInArabic;
    private String visaPurpose;
    private String dateOfIssue;
    private LocalDate dateOfExpiry;
    private LocalDate placeOfIssue;

    private String holderFullNameInArabic;
    private String holderFullName;
    private int holderMoiReference;
    private String holderNationality;
    private LocalDate holderDateOfIssue;
    private String holderGender;
    private String holderOccupation;
    private String holderOccupationInArabic;
    private LocalDate holderDateOfBirth;
    private int holderPassportNo;
    private String holderPlaceOfIssue;
    private String holderPassportType;
    private LocalDate holderExpiryDate;

    private String employerFullName;
    private String employerFullNameInArabic;
    private int employerMoiReference;
    private String employerMobileNumber;

    private boolean active;


}
