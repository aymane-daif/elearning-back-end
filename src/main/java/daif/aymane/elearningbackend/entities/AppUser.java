package daif.aymane.elearningbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Inheritance
public abstract class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appUserId;

    @Min(value = 2, message = "first name is too short")
    @NotEmpty(message = "Please enter first name")
    private String firstName;

    @Min(value = 2, message = "last name is too short")
    @NotEmpty(message = "Please enter last name")
    private String lastName;

    @Column(unique=true)
    @Email(message = "email is invalid")
    @NotEmpty(message = "Please enter email")
    private String email;

    private String phone;

    private String bio;

    private String password;
}
