package com.CarSaleWebsite.Kolesa.MessageSenders;

import com.CarSaleWebsite.Kolesa.DTO.Code;
import com.CarSaleWebsite.Kolesa.DTO.SmsRequest;
import com.CarSaleWebsite.Kolesa.MessageSenders.interfaces.SmsSender;
import com.CarSaleWebsite.Kolesa.Methods.PINGenerator;
import com.CarSaleWebsite.Kolesa.Models.Verification;
import com.CarSaleWebsite.Kolesa.Repositories.VerificationRepository;
import com.CarSaleWebsite.Kolesa.Services.VerificationServiceImpl;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwilioSmsSenderService implements SmsSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSenderService.class);

    public static final String VERIFICATION_CODE = "AITU {Verification code:";

    private final TwilioConfiguration twilioConfiguration;
    @Autowired
    private final VerificationServiceImpl verificationService;

    @Autowired
    public TwilioSmsSenderService(TwilioConfiguration twilioConfiguration, VerificationRepository verificationRepository, VerificationServiceImpl verificationService) {
        this.twilioConfiguration = twilioConfiguration;
        this.verificationService = verificationService;
    }

    @Override
    public void sendSms(SmsRequest smsRequest) {
        if (isPhoneNumberValid(smsRequest.getPhoneNumber())) {
            PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrailNumber());
            String message = smsRequest.getMessage();
            MessageCreator creator = Message.creator(to, from, message);
            creator.create();
            LOGGER.info("Send sms {}", smsRequest);
        } else {
            throw new IllegalArgumentException(
                    "Phone number [" + smsRequest.getPhoneNumber() + "] is not a valid number"
            );
        }

    }

    @Override
    public String sendVerificationCode(com.CarSaleWebsite.Kolesa.DTO.PhoneNumber phoneNumber) {
        SmsRequest smsRequest = null;
        if (isPhoneNumberValid(phoneNumber.getPhoneNumber())) {
            if (!verificationService.isExists(phoneNumber.getPhoneNumber())) {

                PhoneNumber to = new PhoneNumber(phoneNumber.getPhoneNumber());
                PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrailNumber());
                String pinCode = PINGenerator.generate();

                String message = VERIFICATION_CODE + pinCode + "}";
                smsRequest = new SmsRequest(phoneNumber.getPhoneNumber(), message);


                Verification verification = new Verification();
                verification.setPhoneNumber(phoneNumber.getPhoneNumber());
                verification.setPinCode(pinCode);
                verificationService.save(verification);

                MessageCreator creator = Message.creator(to, from, message);
                creator.create();
                LOGGER.info("Send sms {}", smsRequest);
                return phoneNumber.getPhoneNumber();
            } else {
                return "auto-login";
                //TODO:Implement the auto-login in this else
            }
        } else {
            return "error";
        }
    }

    @Override
    public String checkVerificationCode(Code code) {
        if (verificationService.isExistsByCode(code.getCode())) {
            return "auto-login";
        } else {
            return "error";
        }
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        // TODO: Implement phone number validator
        return true;
    }
}
