package daif.aymane.elearningbackend.email;

import daif.aymane.elearningbackend.security.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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

    public boolean isTokenExpired(String token) {
        boolean isExpired;

        try {
            Claims claims = Jwts.parser().setSigningKey(SecurityConstants.TOKEN_SECRET).parseClaimsJws(token)
                    .getBody();

            Date tokenExpirationDate = claims.getExpiration();
            Date todayDate = new Date();

            isExpired = tokenExpirationDate.before(todayDate);
        } catch (ExpiredJwtException ex) {
            isExpired = true;
        }

        return isExpired;
    }
    public String generateEmailVerificationToken(String email) {
        String token = Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET)
                .compact();
        return token;
    }
}
