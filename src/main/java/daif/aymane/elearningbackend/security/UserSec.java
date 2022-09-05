package daif.aymane.elearningbackend.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSec {
    private String userId;
    private String username;

    public static UserSec getLoggedInUser() {
        UserSec currentUser = new UserSec();
        Authentication authentication = SecurityContextHolder.getContext().
                getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Jwt test = (Jwt) SecurityContextHolder.getContext().getAuthentication()
                    .getCredentials();
            currentUser.setUserId(test.getClaim("sub"));
            currentUser.setUsername(test.getClaim("preferred_username"));
        }
        return currentUser;
    }
}