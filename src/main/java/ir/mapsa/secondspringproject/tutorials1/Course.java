package ir.mapsa.secondspringproject.tutorials1;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "THIRD_COURSE")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String topic;
    private Integer unit;
    @Version
    private Integer version;
    @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL)
    private List<Student> students;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
