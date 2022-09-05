package daif.aymane.elearningbackend.controllers;

import org.keycloak.representations.idm.UserRepresentation;
import daif.aymane.elearningbackend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class AppUserController {

    private final UserService userService;

    @GetMapping("/students")
    public List<UserRepresentation> getAllStudents() {
        return userService.getAllStudents();
    }

    @GetMapping("/instructors")
    public List<UserRepresentation> getAllInstructors() {
        return userService.getAllInstructors();
    }

}
