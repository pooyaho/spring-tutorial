package ir.mapsa.secondspringproject;

import ir.mapsa.secondspringproject.tutorials1.models.Student;
import ir.mapsa.secondspringproject.tutorials1.repositories.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Import(SecondSpringProjectApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional(readOnly = false)
@EnableTransactionManagement
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase
@Rollback(false)
class SecondSpringProjectApplicationTests {
    @Autowired
    private StudentRepository studentRepository;
//    @Autowired
//    private TestEntityManager entityManager;

    //    @BeforeAll
//    @Transactional
//    @Rollback(false)
    public void init() {
//        this.studentRepository.deleteAll();
        Student student = Student.builder()

                .family("Alavi")

                .nationalCode("1234567")
                .studentId("1234567")
                .name("Ali")
                .build();
        student.setId(1L);
        this.studentRepository.save(student);
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testStudentRepository() {
//        this.studentRepository.save(Student.builder()
//                .family("Alavi")
//                .nationalCode("1234567")
//                .name("Ali")
//                .build());
        Student student = Student.builder()

                .family("Alavi")

                .nationalCode("1234567")
                .studentId("1234567")
                .name("Ali")
                .build();
        student.setId(1L);
        this.studentRepository.save(student);
        this.studentRepository.deleteAll();
        List<Student> all = this.studentRepository.findAll();
        Assertions.assertThat(all).size().isEqualTo(1);
    }

    @Test()
    public void testDivideReturnsCorrectAnswer() {

        Assertions.assertThat(divide(2, 4)).isEqualTo(0.5f);
        Assertions.assertThat(divide(100, 4)).isEqualTo(25f);

    }

    @Test
    public void testDivideThrowsCorrectException() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            divide(100, 0);
        });
    }

    public float divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException();
        }
        return (float) a / b;
    }

}
