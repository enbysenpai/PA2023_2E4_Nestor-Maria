package org.example.Faculty;

import org.example.DB;
import org.example.Model.Album;
import org.example.Model.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenresFaculty {
    public static boolean create(Genre genre) throws SQLException {
        Connection con = DB.getConnection();
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            pstmt = con.prepareStatement("SELECT id FROM genres WHERE name =?");
            pstmt.setString(1, genre.getName());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                System.err.println("THIS GENRE EXISTS: ID=" + id);
                return false;
            } else {
                pstmt = con.prepareStatement("SELECT COUNT(*) FROM genres");
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    genre.setId(rs.getInt("COUNT(*)") + 1);
                }

                pstmt = con.prepareStatement("INSERT INTO genres (id,name) VALUES (?,?)");
                pstmt.setInt(1, genre.getId());
                pstmt.setString(2, genre.getName());

                pstmt.executeUpdate();
                System.out.println("NEW GENRE ADDED");
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static Genre findByName(String name) throws SQLException {
        Connection con = DB.getConnection();
        PreparedStatement pstmt = con.prepareStatement("SELECT id,name FROM genres WHERE name=?");
        pstmt.setString(1, name);
        ResultSet rs = pstmt.executeQuery();
        {
            while (rs.next()) {
                int id = rs.getInt("id");
                Genre genre = new Genre();
                genre.setId(id);
                genre.setName(name);
                return genre;
            }
        }
        return null;
    }

    public static Genre findById(int id) throws SQLException {
        Connection con = DB.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id,name FROM genres WHERE id='" + id + "'")) {
            if (rs.next()) {
                String name = rs.getString("name");
                Genre genre = new Genre();
                genre.setId(id);
                genre.setName(name);
                return genre;
            }
        }
        return null;
    }

    public static List<Genre> showAll() throws SQLException
    {
        Connection con=DB.getConnection();
        List<Genre>genres=new ArrayList<>();
        try(Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM genres"))
        {
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Genre genre = new Genre();
                genre.setId(id);
                genre.setName(name);
                genres.add(genre);
            }
        }
        return genres;
    }
}


