package dao;

import entity.Student;

import java.util.List;

public interface StudentDAO {
    List<Student> findAll();

    boolean create(Student student);
}
