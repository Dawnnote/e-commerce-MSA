package com.shipshoe.userservice.jwt.provider;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailProvider {

    private final JavaMailSender javaMailSender;

    private final String SUBJECT = "[ShipShoe] 이메일 인증을 위한 인증번호 안내";

    public boolean sendCertificationMail(String email, String certificationNumber) {

        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

            String htmlContent = getCertificationMessage(certificationNumber);

            messageHelper.setTo(email);
            messageHelper.setSubject(SUBJECT);
            messageHelper.setText(htmlContent, true);

            javaMailSender.send(message);

        } catch (Exception exception) {
            exception.getStackTrace();
            return false;
        }

        return true;
    }

    private String getCertificationMessage (String certificationNumber) {

        String certificationMessage = "";
        certificationMessage += "<h1 style='text-align: center;'>ShipShoe 이메일 인증 안내</h1>";
        certificationMessage += "<h3 style='text-align: center;'>인증번호 : <strong style='font-size: 32px; letter-spacing: 8px;'>" + certificationNumber + "</strong></h3>";
        return certificationMessage;
    }

}
