package ir.mapsa.secondspringproject.tutorials1.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "THIRD_STUDENT")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Student extends AbstractEntity {

    @Column(name = "FIRST_NAME", length = 20)
    private String name;
    private String family;

    private Integer passedCourse;
    @Column(unique = true)
    private String nationalCode;
    @Column(unique = true)
    private String studentId;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Course> courses;

}
