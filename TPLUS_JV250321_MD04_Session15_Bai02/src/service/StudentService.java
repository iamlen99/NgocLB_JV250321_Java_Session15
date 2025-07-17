package service;

import entity.Student;

import java.sql.SQLException;
import java.util.Scanner;

public interface StudentService {
    void findAll();

    boolean addStudent(Student student);
}
