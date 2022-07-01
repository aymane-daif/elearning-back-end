package daif.aymane.elearningbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Inheritance
public class AppUser {
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

    private String role;

    private String emailVerificationToken;

    private boolean isEmailVerified = false;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }	, fetch = FetchType.LAZY)
    @JoinTable(
            name = "appUser_item",
            joinColumns = @JoinColumn(name = "appUser_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "authority_id") })
    private List<Authority> authorities = new ArrayList<>();

}
