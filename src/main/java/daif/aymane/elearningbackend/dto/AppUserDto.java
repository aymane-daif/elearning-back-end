package daif.aymane.elearningbackend.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AppUserDto {
    private Long appUserId;

    private String username, firstName, lastName, email, phone, bio, password, role, emailVerificationToken;

    private boolean isEmailVerified;


}
