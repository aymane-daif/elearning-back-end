package daif.aymane.elearningbackend.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AppUserDto {
    private Long appUserId;

    private String firstName, lastName, email, phone, bio, password, role;


}
