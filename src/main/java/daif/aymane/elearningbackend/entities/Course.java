package daif.aymane.elearningbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    private String title;
    private String description;

    private int numberOfStudents;
    private String courseLanguage;

    @CreationTimestamp
    private Date creationDate;

    @UpdateTimestamp
    private Date lastUpdatedDate;

    @OneToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private List<AppUser> appUsers = new ArrayList<>();

    @OneToMany(
    cascade = CascadeType.ALL,
    orphanRemoval = true,
    fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private List<CourseSection> courseSections = new ArrayList<>();
}
