package service.impl;

import com.sun.tools.javac.Main;
import dao.impl.StudentDAOImpl;
import service.StudentService;
import dao.StudentDAO;
import entity.Student;

import java.util.List;
import java.util.Scanner;

public class StudentServiceImpl implements StudentService {
    private final StudentDAO studentDAO;

    public StudentServiceImpl() {
        studentDAO = new StudentDAOImpl();
    }

    @Override
    public void findAll() {
        List<Student> listStudents = studentDAO.findAll();
        listStudents.forEach(System.out::println);
    }

    @Override
    public boolean addStudent(Student student) {
        return studentDAO.create(student);
    }
}
