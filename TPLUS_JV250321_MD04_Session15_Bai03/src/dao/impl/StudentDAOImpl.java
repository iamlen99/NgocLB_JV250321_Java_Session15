package dao.impl;

import dao.StudentDAO;
import entity.Student;
import ulti.ConnectionDB;

import java.sql.*;
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

    @Override
    public boolean update(Student student) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            callStmt = conn.prepareCall("{call update_student(?,?,?,?)}");
            callStmt.setInt(1, student.getStudentId());
            callStmt.setString(2, student.getFullName());
            callStmt.setDate(3, Date.valueOf(student.getDateOfBirth().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            callStmt.setString(4, student.getEmail());
            callStmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            ConnectionDB.closeConnection(conn, callStmt);
        }
        return false;
    }

    @Override
    public boolean isExistId(int id) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            callStmt = conn.prepareCall("{call is_exist_id(?,?)}");
            callStmt.setInt(1, id);
            callStmt.registerOutParameter(2, Types.INTEGER);
            callStmt.execute();
            return callStmt.getInt(2) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            ConnectionDB.closeConnection(conn, callStmt);
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            callStmt = conn.prepareCall("{call delete_student(?)}");
            callStmt.setInt(1, id);
            callStmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            ConnectionDB.closeConnection(conn, callStmt);
        }
        return false;
    }

    @Override
    public Student findById(int id) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            callStmt = conn.prepareCall("{call find_student_by_id(?)}");
            callStmt.setInt(1, id);
            ResultSet rs = callStmt.executeQuery();
            if (rs.next()) {
                Student student = new Student();
                student.setStudentId(id);
                student.setFullName(rs.getString("full_name"));
                student.setEmail(rs.getString("email"));
                student.setDateOfBirth(LocalDate.parse(rs.getString("date_of_birth"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                return student;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            ConnectionDB.closeConnection(conn, callStmt);
        }
        return null;
    }
}
