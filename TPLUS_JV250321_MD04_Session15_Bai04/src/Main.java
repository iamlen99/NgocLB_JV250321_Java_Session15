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
                    updateStudent(scanner);
                    break;

                case 4:
                    deleteStudent(scanner);
                    break;

                case 5:
                    main.studentService.findStudentByName(scanner);
                    break;

                case 6:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Vui long chon tu 1-6");
            }
        } while (true);
    }

    public static Student inputData(Scanner scanner) {
        Student student = new Student();
        student.setStudentId(inputStudentId(scanner, "Nhap ma sinh vien"));
        student.setFullName(inputFullname(scanner, "Nhap ten sinh vien"));
        student.setEmail(inputEmail(scanner, "Nhap email sinh vien"));
        student.setDateOfBirth(inputDateOfBirth(scanner, "Nhap ngay sinh cua sinh vien (dd/MM/yyyy):"));
        return student;
    }

    public static int inputStudentId(Scanner scanner, String message) {
        Main main = new Main();
        System.out.println(message);
        do {
            try {
                int id = Integer.parseInt(scanner.nextLine());
                if (!main.studentService.isExistId(id)) {
                    return id;
                }
                System.out.println("Ma sinh vien da ton tai, vui long nhap lai:");
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap ma sinh vien la so nguyen");
            }
        } while (true);
    }

    public static String inputFullname(Scanner scanner, String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    public static LocalDate inputDateOfBirth(Scanner scanner, String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println(message);
        do {
            try {
                return LocalDate.parse(scanner.nextLine(), formatter);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Dinh dang ngay khong hop le, hay nhap lai:");
            }
        } while (true);
    }

    public static String inputEmail(Scanner scanner, String message) {
        System.out.println(message);
        return scanner.nextLine();
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

    public static void updateStudent(Scanner scanner) {
        Main main = new Main();
        System.out.println("Nhap ma sinh vien cua sinh vien can cap nhat:");
        int updatedId = Integer.parseInt(scanner.nextLine());
        if (main.studentService.isExistId(updatedId)) {
            Student student = main.studentService.getStudentById(updatedId);
            if (student == null) {
                System.out.println("Co loi trong qua trinh tim sinh vien");
                return;
            }
            boolean isExit = false;
            do {
                System.out.println("1. Cap nhat ten sinh vien");
                System.out.println("2. Cap nhat ngay sinh");
                System.out.println("3. Cap nhat email cua sinh vien");
                System.out.println("4. Thoat");
                System.out.println("Lua chon cua ban:");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        String newName = inputFullname(scanner, "Nhap ten sinh vien moi:");
                        student.setFullName(newName);
                        break;

                    case 2:
                        LocalDate newDOB = inputDateOfBirth(scanner, "Nhap ngay sinh moi cua sinh vien:");
                        student.setDateOfBirth(newDOB);
                        break;

                    case 3:
                        String newEmail = inputFullname(scanner, "Nhap email moi cua sinh vien:");
                        student.setEmail(newEmail);
                        break;

                    case 4:
                        isExit = true;
                        break;

                    default:
                        System.out.println("Vui long chon tu 1-4");

                }
            } while (!isExit);

            if (main.studentService.updateStudent(student)) {
                System.out.println("Cap nhat sinh vien thanh cong");
            } else {
                System.out.println("Co loi trong qua trinh cap nhat sinh vien");
            };
        } else {
            System.out.println("Khong tim thay sinh vien co id ban vua nhap");
        }
    }

    public static void deleteStudent (Scanner scanner) {
        Main main = new Main();
        System.out.println("Nhap ma sinh vien cua sinh vien can xoa:");
        int id = Integer.parseInt(scanner.nextLine());
        if (main.studentService.isExistId(id)) {
            if (main.studentService.deleteStudent(id)) {
                System.out.println("Xoa sinh vien thanh cong");
            } else {
                System.out.println("Co loi trong qua trinh xoa sinh vien");
            }
        } else {
            System.out.println("Khong tim thay sinh vien co id ban vua nhap");
        }
    }
}
