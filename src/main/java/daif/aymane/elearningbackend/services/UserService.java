package daif.aymane.elearningbackend.services;

import daif.aymane.elearningbackend.dto.AppUserDto;
import daif.aymane.elearningbackend.entities.AppUser;
import daif.aymane.elearningbackend.entities.Student;
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findUserByEmail(email).get();

        if (!appUserRepository.findUserByEmail(email).isPresent())
            throw new UsernameNotFoundException(email);

        return new User(appUser.getEmail(), appUser.getPassword(),
                true,
                true, true,
                true, new ArrayList<>());

    }

    public AppUserDto createUser(AppUserDto appUserDto) {

        if (appUserRepository.findUserByEmail(appUserDto.getEmail()).isPresent())
            throw new IllegalStateException("Record already exists");


        AppUser appUser = new AppUser();
        BeanUtils.copyProperties(appUserDto, appUser);
        appUser.setPassword(bCryptPasswordEncoder.encode(appUserDto.getPassword()));


        appUserRepository.save(appUser);
        BeanUtils.copyProperties(appUser, appUserDto);

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
}
