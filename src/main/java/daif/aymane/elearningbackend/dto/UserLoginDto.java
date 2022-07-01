package daif.aymane.elearningbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserLoginDto {
    private String email;
    private String password;
}
