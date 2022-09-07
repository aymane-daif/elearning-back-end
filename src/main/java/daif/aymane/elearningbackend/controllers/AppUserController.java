package daif.aymane.elearningbackend.controllers;

import daif.aymane.elearningbackend.dto.AppUserDto;
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

    @GetMapping(path = "/username/{username}")
    public List<UserRepresentation> getUserByUsername(@PathVariable("username") String username){
        return userService.getUserByUsername(username);
    }

    @GetMapping(path = "/id/{userId}")
    public UserRepresentation getUserById(@PathVariable("userId") String userId){
        return userService.getUserById(userId);
    }

    @PostMapping
    public String addUser(@RequestBody AppUserDto appUserDto){
        userService.createUser(appUserDto);
        return "User Added Successfully.";
    }

    @DeleteMapping(path = "/id/{userId}")
    public String deleteUser(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
        return "User Deleted Successfully.";
    }

}
