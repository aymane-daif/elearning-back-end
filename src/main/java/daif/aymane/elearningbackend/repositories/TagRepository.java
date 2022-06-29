package daif.aymane.elearningbackend.repositories;

import daif.aymane.elearningbackend.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
