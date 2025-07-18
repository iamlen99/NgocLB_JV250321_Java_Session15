package service.impl;

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

    @Override
    public boolean updateStudent(Student student) {
        return studentDAO.update(student);
    }

    @Override
    public boolean isExistId(int id) {
        return studentDAO.isExistId(id);
    }

    @Override
    public boolean deleteStudent(int id) {
        return studentDAO.delete(id);
    }

    @Override
    public Student getStudentById(int id) {
        return studentDAO.findById(id);
    }

    @Override
    public void findStudentByName(Scanner scanner) {
        System.out.println("Nhap ten sinh vien ban muon tim:");
        String searchName = scanner.nextLine();
        List<Student> listStudents = studentDAO.findByName(searchName);
        if (listStudents == null || listStudents.isEmpty()) {
            System.out.println("Khong tim thay sinh vien co ten ban vua nhap");
        } else {
            listStudents.forEach(System.out::println);
        }
    }
}
