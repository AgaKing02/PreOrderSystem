package com.CarSaleWebsite.Kolesa.Controllers;

import com.CarSaleWebsite.Kolesa.DTO.Code;
import com.CarSaleWebsite.Kolesa.DTO.PhoneNumber;
import com.CarSaleWebsite.Kolesa.DTO.SmsRequest;
import com.CarSaleWebsite.Kolesa.Services.SmsSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/sms")
public class PhoneNumberValidationController {

    private final SmsSenderService service;

    @Autowired
    public PhoneNumberValidationController(SmsSenderService service) {
        this.service = service;
    }


    @PostMapping
    public void sendSms(@Valid @RequestBody SmsRequest smsRequest) {
        service.sendSms(smsRequest);
    }

    @PostMapping("/code")
    public @ResponseBody
    ResponseEntity<?> sendVerificationCode(@Valid @RequestBody PhoneNumber phoneNumber) {
        return getStringResponseEntity(phoneNumber);
    }

    private ResponseEntity<String> getStringResponseEntity(PhoneNumber phoneNumber) {
        if (phoneNumber.getPhoneNumber().length() == 12) {
            if (service.sendVerificationCode(phoneNumber).equals(phoneNumber.getPhoneNumber())) {
                return ResponseEntity.ok("Completed successfully,check the phone");
            } else if (service.sendVerificationCode(phoneNumber).equals("error")) {
                return ResponseEntity.badRequest().body("Completed unsuccessfully,phone is not valid...");
            } else {
                System.out.println("Auto Login Started");
            }
        } else {
            return ResponseEntity.badRequest().body("Completed unsuccessfully,bad credentials...");
        }
        return null;
    }

    @PostMapping("/check")
    public @ResponseBody
    ResponseEntity<?> checkVerificationCode(@Valid @RequestBody Code code) {
        return getStringResponseEntity(code);
    }

    private ResponseEntity<String> getStringResponseEntity(Code code) {
        if (code.getCode().length() == 4) {
            if (service.checkVerificationCode(code).equals("auto-login")) {
                return ResponseEntity.ok("Auto-Login started");
            } else {
                return ResponseEntity.badRequest().body("Could not found");
            }
        } else {
            return ResponseEntity.badRequest().body("Completed unsuccessfully,bad credentials...");
        }
    }
}