package com.CarSaleWebsite.Kolesa.MessageSenders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsSenderService {
    private final SmsSender smsSender;

    @Autowired
    public SmsSenderService(TwilioSmsSender smsSender) {
        this.smsSender = smsSender;
    }

    public void sendSms(SmsRequest smsRequest) {
        smsSender.sendSms(smsRequest);
    }
}
