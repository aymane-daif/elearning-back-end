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
public class CourseContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseContentId;

    private String courseLength;
    private int numberOfVideos;
    private int numberOfSections;
}
