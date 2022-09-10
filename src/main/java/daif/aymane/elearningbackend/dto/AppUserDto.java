package daif.aymane.elearningbackend.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AppUserDto {
    private Long appUserId;

    private String firstName, lastName, email, phone, bio, password, role, emailVerificationToken;

    private boolean isEmailVerified;


}
