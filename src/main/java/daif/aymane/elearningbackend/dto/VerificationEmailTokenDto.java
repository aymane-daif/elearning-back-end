package daif.aymane.elearningbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class VerificationEmailTokenDto {
    private String token;
}
