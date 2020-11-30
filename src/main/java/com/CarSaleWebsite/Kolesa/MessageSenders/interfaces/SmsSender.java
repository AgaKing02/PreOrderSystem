package com.CarSaleWebsite.Kolesa.MessageSenders.interfaces;

import com.CarSaleWebsite.Kolesa.DTO.Code;
import com.CarSaleWebsite.Kolesa.DTO.PhoneNumber;
import com.CarSaleWebsite.Kolesa.DTO.SmsRequest;
import org.springframework.http.ResponseEntity;

public interface SmsSender {

    void sendSms(SmsRequest smsRequest);

    String sendVerificationCode(PhoneNumber phoneNumber);

    String checkVerificationCode(Code code);
}
