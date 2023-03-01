package ir.mapsa.secondspringproject.tutorials1;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "THIRD_STUDENT")
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public Integer getPassedCourse() {
        return passedCourse;
    }

    public void setPassedCourse(Integer passedCourse) {
        this.passedCourse = passedCourse;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    
}
