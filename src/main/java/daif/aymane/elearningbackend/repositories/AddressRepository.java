package daif.aymane.elearningbackend.repositories;

import daif.aymane.elearningbackend.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
