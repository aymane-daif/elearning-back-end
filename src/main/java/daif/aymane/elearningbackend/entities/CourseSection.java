package daif.aymane.elearningbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class CourseSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseSectionId;

    private String sectionTitle;
    private String sectionLength;
    private int numberOfVideos;
}
