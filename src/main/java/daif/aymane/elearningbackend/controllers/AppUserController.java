package daif.aymane.elearningbackend.controllers;

import daif.aymane.elearningbackend.dto.AppUserDto;
import daif.aymane.elearningbackend.dto.VerificationEmailTokenDto;
import daif.aymane.elearningbackend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AppUserController {

    private final UserService userService;

    @PostMapping("/signup")
    public AppUserDto createUser(@RequestBody AppUserDto appUserDto){
        return userService.createUser(appUserDto);
    }

    @PostMapping("/email-verification")
    public boolean verifyEmailToken(@RequestBody VerificationEmailTokenDto verificationEmailTokenDto) {

       return userService.verifyEmailToken(verificationEmailTokenDto.getToken());

    }
}
