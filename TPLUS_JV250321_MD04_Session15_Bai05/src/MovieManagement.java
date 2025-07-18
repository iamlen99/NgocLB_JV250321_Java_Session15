
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieManagement {
    public boolean addMovie(String title, String director, int year) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callStmt = conn.prepareCall("{CALL add_movie(?,?,?)}");
            callStmt.setString(1, title);
            callStmt.setString(2, director);
            callStmt.setInt(3, year);
            int rows = callStmt.executeUpdate();
            if (rows > 0) {
                conn.commit();
                return true;
            }

        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callStmt);
        }
        return false;
    }

    public List<Movie> listMovies() {
        Connection conn = null;
        CallableStatement callStmt = null;
        List<Movie> movies = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callStmt = conn.prepareCall("{CALL list_movies()}");

            ResultSet rs = callStmt.executeQuery();
            movies = new ArrayList<>();
            while (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getInt("movie_id"));
                movie.setTitle(rs.getString("title"));
                movie.setDirector(rs.getString("director"));
                movie.setYear(rs.getString("year"));
                movies.add(movie);
            }
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callStmt);
        }
        return movies;
    }

    public boolean updateMovie(int id, String title, String director, int year) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callStmt = conn.prepareCall("{CALL update_movie(?,?,?,?)}");
            callStmt.setInt(1, id);
            callStmt.setString(2, title);
            callStmt.setString(3, director);
            callStmt.setInt(4, year);
            int rows = callStmt.executeUpdate();
            if (rows > 0) {
                conn.commit();
                return true;
            }
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callStmt);
        }
        return false;
    }

    public boolean deleteMovie(int id) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            conn.setAutoCommit(false);
            callStmt = conn.prepareCall("{CALL delete_movie(?)}");
            callStmt.setInt(1, id);
            int rows = callStmt.executeUpdate();
            if (rows > 0) {
                conn.commit();
                return true;
            }
            conn.rollback();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callStmt);
        }
        return false;
    }
}
