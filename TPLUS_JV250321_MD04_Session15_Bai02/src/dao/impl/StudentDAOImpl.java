package dao.impl;

import dao.StudentDAO;
import entity.Student;
import ulti.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {


    @Override
    public List<Student> findAll() {
        Connection conn = null;
        CallableStatement CallSt = null;
        List<Student> ListStudents = null;
        try {
            conn = ConnectionDB.openConnection();
            CallSt = conn.prepareCall("{call get_all_students()}");
            ResultSet rs = CallSt.executeQuery();
            ListStudents = new ArrayList<>();
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setFullName(rs.getString("full_name"));
                student.setEmail(rs.getString("email"));
                student.setDateOfBirth(LocalDate.parse(rs.getString("date_of_birth"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                ListStudents.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, CallSt);
        }
        return ListStudents;
    }

    @Override
    public boolean create(Student student) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            callStmt = conn.prepareCall("{call add_student(?,?,?)}");
            callStmt.setString(1, student.getFullName());
            callStmt.setDate(2, Date.valueOf(student.getDateOfBirth().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            callStmt.setString(3, student.getEmail());
            callStmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            ConnectionDB.closeConnection(conn, callStmt);
        }
        return false;
    }
}
