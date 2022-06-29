package daif.aymane.elearningbackend.repositories;

import daif.aymane.elearningbackend.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
