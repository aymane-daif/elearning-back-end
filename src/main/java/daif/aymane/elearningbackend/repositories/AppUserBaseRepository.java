package daif.aymane.elearningbackend.repositories;

import daif.aymane.elearningbackend.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface AppUserBaseRepository<T extends AppUser> extends JpaRepository<T, Long> {
    Optional<AppUser> findUserByEmail(String email);
}
