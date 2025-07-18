public class Movie {
    private int id;
    private String title;
    private String director;
    private String year;

    public Movie() {
    }

    public Movie(int id, String title, String director, String year) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return String.format("ID: %d - Title: %s - Director: %s - Year: %s"
                , this.id, this.title, this.director, this.year);
    }
}
