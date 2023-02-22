package ir.mapsa.secondspringproject.tutorials1;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentRepository implements BaseStudentRepository {
    @Override
    public void add(Student student) throws Exception {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("insert into second_student(name, family, passed_course, student_id, national_code) values (?,?,?,?,?)")) {
                statement.setString(1, student.getName());
                statement.setString(2, student.getFamily());
                statement.setInt(3, student.getPassedCourse());
                statement.setString(4, student.getStudentId());
                statement.setString(5, student.getNationalCode());
                statement.executeUpdate();
            }
        }
    }

    @Override
    public void update(Student student) throws Exception {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("update second_student set name=?, family=?, passed_course=?, student_id=?, national_code=?\n" +
                    "where id=?")) {
                statement.setString(1, student.getName());
                statement.setString(2, student.getFamily());
                statement.setInt(3, student.getPassedCourse());
                statement.setString(4, student.getStudentId());
                statement.setString(5, student.getNationalCode());
                statement.setLong(6, student.getId());
                statement.executeUpdate();
            }
        }
    }

    @Override
    public void removeById(Long id) throws Exception {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("delete from second_student where id=?")) {
                statement.setLong(1, id);
                statement.executeUpdate();
            }
        }
    }

    @Override
    public Student findById(Long id) throws Exception {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("select * from second_student where id=?")) {

                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Student student = new Student();
                        student.setStudentId(resultSet.getString("student_id"));
                        student.setFamily(resultSet.getString("family"));
                        student.setName(resultSet.getString("name"));
                        student.setNationalCode(resultSet.getString("national_code"));
                        student.setPassedCourse(resultSet.getInt("passed_course"));
                        student.setId(id);
                        return student;

                    }else{
                        return null;
                    }
                }
            }
        }
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/second_class", "root", null);
    }

    @Override
    public List<Student> getAll() throws Exception{
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("select * from second_student")) {


                try (ResultSet resultSet = statement.executeQuery()) {
                    List<Student> result = new ArrayList<>();
                    while(resultSet.next()) {
                        Student student = new Student();
                        student.setStudentId(resultSet.getString("student_id"));
                        student.setFamily(resultSet.getString("family"));
                        student.setName(resultSet.getString("name"));
                        student.setNationalCode(resultSet.getString("national_code"));
                        student.setPassedCourse(resultSet.getInt("passed_course"));
                        student.setId(resultSet.getLong("id"));
                        result.add(student);
                    }
                    return result;
                }
            }
        }



    }
}
