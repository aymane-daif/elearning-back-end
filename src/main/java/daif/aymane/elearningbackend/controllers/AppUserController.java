package daif.aymane.elearningbackend.controllers;

import daif.aymane.elearningbackend.dto.AppUserDto;
import daif.aymane.elearningbackend.entities.AppUser;
import daif.aymane.elearningbackend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AppUserController {

    private final UserService userService;

    @PostMapping("/signup")
    public AppUserDto createUser(@RequestBody AppUserDto appUserDto){
        return userService.createUser(appUserDto);
    }


}
