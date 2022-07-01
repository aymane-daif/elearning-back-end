package daif.aymane.elearningbackend.services;

import daif.aymane.elearningbackend.dto.AppUserDto;
import daif.aymane.elearningbackend.email.EmailEntity;
import daif.aymane.elearningbackend.email.EmailService;
import daif.aymane.elearningbackend.entities.AppUser;
import daif.aymane.elearningbackend.repositories.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailService emailService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findUserByEmail(email).get();

        if (!appUserRepository.findUserByEmail(email).isPresent())
            throw new UsernameNotFoundException(email);

        return new User(appUser.getEmail(), appUser.getPassword(),
                appUser.isEmailVerified(),
                true, true,
                true, new ArrayList<>());

    }

    public AppUserDto createUser(AppUserDto appUserDto) {

        if (appUserRepository.findUserByEmail(appUserDto.getEmail()).isPresent())
            throw new IllegalStateException("Record already exists");


        AppUser appUser = new AppUser();
        BeanUtils.copyProperties(appUserDto, appUser);
        appUser.setPassword(bCryptPasswordEncoder.encode(appUserDto.getPassword()));
        appUser.setEmailVerificationToken(emailService.generateEmailVerificationToken(appUserDto.getEmail()));
        appUser.setEmailVerified(false);
        appUserRepository.save(appUser);
        BeanUtils.copyProperties(appUser, appUserDto);

        String subject = "Email verification token";
        String msg = "Your verification token is: " + appUser.getEmailVerificationToken();
        EmailEntity emailEntity = new EmailEntity(appUserDto.getEmail(),subject,msg);
        emailService.sendMail(emailEntity);

        return appUserDto;
    }

    public AppUserDto getUser(String email) {
        AppUser appUser = appUserRepository.findUserByEmail(email).get();

        if (!appUserRepository.findUserByEmail(email).isPresent())
            throw new UsernameNotFoundException(email);

        AppUserDto appUserDto = new AppUserDto();
        BeanUtils.copyProperties(appUser, appUserDto);

        return appUserDto;
    }

    public boolean verifyEmailToken(String token) {
        boolean isValidToken = false;

        AppUser appUser = appUserRepository.findUserByEmailVerificationToken(token).get();

        if (appUserRepository.findUserByEmailVerificationToken(token).isPresent()) {
            boolean hasTokenExpired = emailService.isTokenExpired(token);
            if (!hasTokenExpired) {
                appUser.setEmailVerificationToken(null);
                appUser.setEmailVerified(true);
                appUserRepository.save(appUser);
                isValidToken = true;
            }
        }

        return isValidToken;
    }
}
