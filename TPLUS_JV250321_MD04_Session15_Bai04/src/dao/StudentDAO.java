package dao;

import entity.Student;

import java.util.List;
import java.util.Scanner;

public interface StudentDAO {
    List<Student> findAll();

    boolean create(Student student);

    boolean update(Student student);

    boolean isExistId (int id);

    boolean delete(int id);

    Student findById(int id);

    List<Student> findByName(String name);
}
