package daif.aymane.elearningbackend.repositories;

import daif.aymane.elearningbackend.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
