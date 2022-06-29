package daif.aymane.elearningbackend.repositories;

import daif.aymane.elearningbackend.entities.AppUser;

import javax.transaction.Transactional;

@Transactional
public interface AppUserRepository extends AppUserBaseRepository<AppUser> {
}
