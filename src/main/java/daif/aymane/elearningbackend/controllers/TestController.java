package daif.aymane.elearningbackend.controllers;

import daif.aymane.elearningbackend.dto.AppUserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public AppUserDto test() {
        AppUserDto appUserDto = AppUserDto.builder().firstName("hello test").build();
        return appUserDto;
    }
}
