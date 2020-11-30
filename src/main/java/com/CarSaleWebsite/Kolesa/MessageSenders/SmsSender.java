package com.CarSaleWebsite.Kolesa.MessageSenders;

import javax.validation.Valid;

public interface SmsSender {

    void sendSms(@Valid SmsRequest smsRequest);
}
