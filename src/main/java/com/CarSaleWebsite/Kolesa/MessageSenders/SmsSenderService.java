package com.CarSaleWebsite.Kolesa.MessageSenders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class SmsSenderService {
    private final SmsSender smsSender;

    @Autowired
    public SmsSenderService(TwilioSmsSender smsSender) {
        this.smsSender = smsSender;
    }

    public void sendSms(@Valid SmsRequest phoneNumber) {
        smsSender.sendSms(phoneNumber);
    }
}
