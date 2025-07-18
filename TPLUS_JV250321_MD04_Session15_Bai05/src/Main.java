import java.util.List;
import java.util.Scanner;

public class Main {
    public static MovieManagement movieManagement = new MovieManagement();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("***************MENU*****************");
            System.out.println("1. Them phim");
            System.out.println("2. Liet ke phim");
            System.out.println("3. Sua phim");
            System.out.println("4. Xoa phim");
            System.out.println("5. Thoat");
            System.out.println("Lua chon cua ban:");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("Nhap tieu de phim:");
                    String title = scanner.nextLine();
                    System.out.println("Nhap ten dao dien:");
                    String director = scanner.nextLine();
                    System.out.println("Nhap nam phat hanh:");
                    int year = Integer.parseInt(scanner.nextLine());

                    if (movieManagement.addMovie(title, director, year)) {
                        System.out.println("Them phim thanh cong");
                    } else {
                        System.out.println("Co loi trong qua trinh them phim");
                    };
                    break;

                case 2:
                    List<Movie> listMovies = movieManagement.listMovies();
                    listMovies.forEach(System.out::println);
                    break;

                case 3:
                    System.out.println("Nhap id firm can cap nhat:");
                    int idUpdate = Integer.parseInt(scanner.nextLine());
                    System.out.println("Nhap tieu de phim moi:");
                    String titleUpdate = scanner.nextLine();
                    System.out.println("Nhap ten dao dien moi:");
                    String directorUpdate = scanner.nextLine();
                    System.out.println("Nhap nam phat hanh moi:");
                    int yearUpdate = Integer.parseInt(scanner.nextLine());

                    if (movieManagement.updateMovie(idUpdate, titleUpdate, directorUpdate, yearUpdate)) {
                        System.out.println("Them phim thanh cong");
                    } else {
                        System.out.println("Co loi trong qua trinh them phim hoac khong tim thay id");
                    };
                    break;

                case 4:
                    System.out.println("Nhap id firm can xoa:");
                    int idDelete = Integer.parseInt(scanner.nextLine());

                    if (movieManagement.deleteMovie(idDelete)) {
                        System.out.println("Xoa phim thanh cong");
                    } else {
                        System.out.println("Co loi trong qua trinh xoa phim hoac khong tim thay id");
                    }
                    break;

                case 5:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Vui long chon tu 1-5");
            }
        } while (true);
    }
}
