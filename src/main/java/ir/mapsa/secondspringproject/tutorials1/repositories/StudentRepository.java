package ir.mapsa.secondspringproject.tutorials1.repositories;

import ir.mapsa.secondspringproject.tutorials1.exceptions.IdNotFoundException;
import ir.mapsa.secondspringproject.tutorials1.exceptions.ServiceException;
import ir.mapsa.secondspringproject.tutorials1.models.Student;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentRepository implements BaseRepository<Student> {
    private static Student getStudent(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setStudentId(resultSet.getString("student_id"));
        student.setFamily(resultSet.getString("family"));
        student.setName(resultSet.getString("name"));
        student.setNationalCode(resultSet.getString("national_code"));
        student.setPassedCourse(resultSet.getInt("passed_course"));
        student.setId(resultSet.getLong("id"));
        return student;
    }

    @Override
    public void add(Student student) throws ServiceException {
        executeUpdateQuery(
                "insert into second_student(name, family, passed_course, student_id, national_code) values (?,?,?,?,?)",
                student);
    }

    private void executeUpdateQuery(String query, Student student) throws ServiceException {
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
        } catch (SQLException e) {
            throw new ServiceException("Error in creating connection", e, "database-exception");
        }
    }

    @Override
    public void update(Student student) throws ServiceException {
        executeUpdateQuery(
                "update second_student set name=?, family=?, passed_course=?, student_id=?, national_code=?\n" +
                        "where id=?", student);
    }

    @Override
    public void removeById(Long id) throws ServiceException {
        if (this.findById(id) == null) {
            throw new IdNotFoundException("student-notfound");
        }
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("delete from second_student where id=?")) {
                statement.setLong(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new ServiceException("Error in creating connection", e, "database-exception");
        }
    }

    @Override
    public Student findById(Long id) throws ServiceException {
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
        } catch (SQLException e) {
            throw new ServiceException("Error in creating connection", e, "database-exception");
        }
    }

    private Connection getConnection() throws ServiceException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/second_class", "root", null);
        } catch (Exception e) {
            throw new ServiceException("Error in creating connection", e, "database-exception");
        }
    }

    @Override
    public List<Student> getAll() throws ServiceException {
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
        } catch (SQLException e) {
            throw new ServiceException("Error in creating connection", e, "database-exception");
        }
    }

    public List<Student> findByExample(Student student) {
        String query = "select * from second_student where 1=1 ";
        if (student.getId() != null) {
            query += " AND id = " + student.getId();
        }

        if (student.getStudentId() != null) {
            query += " AND student_id = '" + student.getStudentId() + "'";
        }
        //todo implement this method
        return null;
    }
}
