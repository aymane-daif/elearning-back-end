package daif.aymane.elearningbackend.email;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class EmailService {
    @Autowired
    private  JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;

    public void sendMail(EmailEntity emailEntity) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setFrom(sender);
            simpleMailMessage.setTo(emailEntity.getReceiver());
            simpleMailMessage.setText(emailEntity.getMsg());
            simpleMailMessage.setSubject(emailEntity.getSubject());

            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
