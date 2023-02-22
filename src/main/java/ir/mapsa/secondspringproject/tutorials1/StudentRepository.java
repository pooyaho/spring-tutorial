package ir.mapsa.secondspringproject.tutorials1;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentRepository implements BaseRepository<Student> {
    @Override
    public void add(Student student) throws Exception {
        executeUpdateQuery("insert into second_student(name, family, passed_course, student_id, national_code) values (?,?,?,?,?)", student);
    }

    private void executeUpdateQuery(String query,Student student) throws Exception {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, student.getName());
                statement.setString(2, student.getFamily());
                statement.setInt(3, student.getPassedCourse());
                statement.setString(4, student.getStudentId());
                statement.setString(5, student.getNationalCode());
                if (student.getId() != null) {
                    statement.setLong(6, student.getId());
                }
                statement.executeUpdate();
            }
        }
    }

    @Override
    public void update(Student student) throws Exception {
        executeUpdateQuery("update second_student set name=?, family=?, passed_course=?, student_id=?, national_code=?\n" +
                "where id=?",student);
    }

    @Override
    public void removeById(Long id) throws Exception {
        if (this.findById(id) == null) {
            throw new IllegalArgumentException();
        }
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
                        Student student = getStudent(resultSet);
                        return student;

                    } else {
                        return null;
                    }
                }
            }
        }
    }

    private static Student getStudent( ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setStudentId(resultSet.getString("student_id"));
        student.setFamily(resultSet.getString("family"));
        student.setName(resultSet.getString("name"));
        student.setNationalCode(resultSet.getString("national_code"));
        student.setPassedCourse(resultSet.getInt("passed_course"));
        student.setId(resultSet.getLong("id"));
        return student;
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/second_class", "root", null);
    }

    @Override
    public List<Student> getAll() throws Exception {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("select * from second_student")) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    List<Student> result = new ArrayList<>();
                    while (resultSet.next()) {
                        Student student = getStudent(resultSet);
                        result.add(student);
                    }
                    return result;
                }
            }
        }
    }

    public List<Student> findByExample(Student student) {
        String query = "select * from second_student where 1=1 ";
        if (student.getId() != null) {
            query += " AND id = "+student.getId();
        }

        if (student.getStudentId() != null) {
            query += " AND student_id = '"+ student.getStudentId()+"'";
        }
        //todo implement this method
        return null;
    }
}
