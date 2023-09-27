package uz.pdp.jakarta_ee.jakarta_ee.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class SendingMessageForUser {
    public Boolean sendMessage(String recipient, String link) {
        try {
            Properties properties = new Properties();
            properties.put("mail.transport.protocol", "smtp");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.debug", "true");
//        return mailSender;

            Properties newProperties = new Properties();
            newProperties.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
            newProperties.put("mail.smtp.port", "587");
            newProperties.put("mail.smtp.starttls.enable", "true");
            newProperties.put("mail.smtp.auth", "true");
            String username = "2dc6377fa34705";
            String password = "d27778d3a077b3";
            Session session = getSession(newProperties, username, password);
            Message message = new MimeMessage(session);
            message.setSubject("Test uchun subjecct");

            Multipart multipart=new MimeMultipart();

            BodyPart bodyPart=new MimeBodyPart();

            bodyPart.setText("Qo'shiq nomi");
            bodyPart.setText("Rasm");

            MimeBodyPart mimeBodyPart=new MimeBodyPart();

            mimeBodyPart.attachFile(new File("D:\\media va darslar\\Qo'shiqlar\\2731360.mp3"));
            MimeBodyPart photo=new MimeBodyPart();
            photo.attachFile(new File("D:\\media"));
            mimeBodyPart.attachFile(new File("D:\\media darslar\\Qo'shiqlar\\2.jpg'"));

            multipart.addBodyPart(bodyPart);
            multipart.addBodyPart(mimeBodyPart);
            multipart.addBodyPart(photo);
            message.setContent(multipart);

            String mess = String.format("<h1><a href='/%s'>Tasdiqlang</a></h1>", link);
            message.setContent(mess,"text/html");
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private Session getSession(Properties newProperties, String username, String password) {
        return Session.getInstance(newProperties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }
}
