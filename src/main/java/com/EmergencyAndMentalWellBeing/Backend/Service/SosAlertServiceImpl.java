package com.EmergencyAndMentalWellBeing.Backend.Service;

import com.EmergencyAndMentalWellBeing.Backend.Model.SosAlert;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Service
public class SosAlertServiceImpl implements SosAlertService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    @Value("${mail.to.address}")
    private String emergencyEmail;

    private final JavaMailSender javaMailSender;

    public SosAlertServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public boolean sendSosNotification(SosAlert sosAlert) {
        try {
            // Send SMS using Twilio
            Twilio.init(accountSid, authToken);

            Message.creator(
                    new com.twilio.type.PhoneNumber(sosAlert.getPhoneNumber()),
                    new com.twilio.type.PhoneNumber(twilioPhoneNumber),
                    sosAlert.getMessage()
            ).create();

            // Send Email using JavaMailSender
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(emergencyEmail);
            mailMessage.setSubject("SOS Alert");
            mailMessage.setText("Emergency Message: " + sosAlert.getMessage() +
                    "\nPhone Number: " + sosAlert.getPhoneNumber());

            javaMailSender.send(mailMessage);

            System.out.println("SOS Alert sent successfully via SMS and Email!");
            return true;
        } catch (Exception e) {
            System.err.println("Error sending SOS alert: " + e.getMessage());
            return false;
        }
    }
}
