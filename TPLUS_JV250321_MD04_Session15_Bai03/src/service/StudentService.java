package service;

import entity.Student;

import java.sql.SQLException;
import java.util.Scanner;

public interface StudentService {
    void findAll();

    boolean addStudent(Student student);

    boolean updateStudent(Student student);

    boolean isExistId(int id);

    boolean deleteStudent(int id);

    Student getStudentById(int id);
}
