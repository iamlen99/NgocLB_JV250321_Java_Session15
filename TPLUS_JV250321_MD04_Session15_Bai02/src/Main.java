import entity.Student;
import service.StudentService;
import service.impl.StudentServiceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private final StudentService studentService;

    public Main() {
        studentService = new StudentServiceImpl();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();

        do {
            System.out.println("***********MENU QUAN LY SINH VIEN*************");
            System.out.println("1. Hien thi danh sach sinh vien");
            System.out.println("2. Them moi sinh vien");
            System.out.println("3. Sua sinh vien");
            System.out.println("4. Xoa sinh vien");
            System.out.println("5. Tim kiem sinh vien");
            System.out.println("6. Thoat");
            System.out.println("Lua chon cua ban:");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                main.studentService.findAll();
                    break;

                case 2:
                    addStudent(scanner);
                    break;

                case 3:
                    break;

                case 4:

                    break;

                case 5:
                    break;

                case 6:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Vui long chon tu 1-6");
            }
        } while (true);
    }

    public static Student inputData (Scanner scanner) {
        Student student = new Student();
        student.setStudentId(inputStudentId(scanner));
        student.setFullName(inputFullname(scanner));
        student.setEmail(inputEmail(scanner));
        student.setDateOfBirth(inputDateOfBirth(scanner));
        return student;
    }

    public static int inputStudentId(Scanner scanner) {
        System.out.println("Nhap ma sinh vien");
        return Integer.parseInt(scanner.nextLine());
    }

    public static String inputFullname(Scanner scanner) {
        System.out.println("Nhap ten sinh vien:");
        return  scanner.nextLine();
    }

    public static LocalDate inputDateOfBirth(Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Nhap ngay sinh cua sinh vien (dd/MM/yyyy):");
        return LocalDate.parse(scanner.nextLine(), formatter);
    }

    public static String inputEmail(Scanner scanner) {
        System.out.println("Nhap email sinh vien");
        return  scanner.nextLine();
    }

    public static void addStudent(Scanner scanner) {
        Main main = new Main();
        Student student = inputData(scanner);
        if (main.studentService.addStudent(student)) {
            System.out.println("Them sinh vien thanh cong");
        } else {
            System.out.println("Co loi trong qua trinh them sinh vien");
        }
    }
}
