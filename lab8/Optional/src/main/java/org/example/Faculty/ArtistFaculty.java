package org.example.Faculty;

import org.example.DB;
import org.example.Model.Artist;

import java.sql.*;

public class ArtistFaculty {
    public static boolean create(Artist artist) throws SQLException {
        Connection con = DB.getConnection();
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            pstmt = con.prepareStatement("SELECT id FROM artists WHERE name=?");
            pstmt.setString(1, artist.getName());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                System.err.println("THIS ARTIST EXISTS: ID=" + id);
                return false;
            } else {
                pstmt = con.prepareStatement("SELECT COUNT(*) FROM artists");
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    artist.setId(rs.getInt("COUNT(*)") + 1);
                }

                pstmt = con.prepareStatement("INSERT INTO artists (id,name) VALUES (?,?)");
                pstmt.setInt(1, artist.getId());
                pstmt.setString(2, artist.getName());

                pstmt.executeUpdate();
                System.out.println("NEW ARTIST ADDED");
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static Artist findByName(String name) throws SQLException {
        Connection con = DB.getConnection();
        PreparedStatement pstmt = con.prepareStatement("SELECT id,name FROM artists WHERE name=?");
        pstmt.setString(1, name);
        ResultSet rs = pstmt.executeQuery();
        {
            if (rs.next()) {
                int id = rs.getInt("id");
                Artist artist = new Artist();
                artist.setId(id);
                artist.setName(name);
                return artist;
            }
        }
        return null;
    }

    public static Artist findById(int id) throws SQLException {
        Connection con = DB.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id,name FROM artists WHERE id='" + id + "'")) {
            while (rs.next()) {
                String name = rs.getString("name");
                Artist artist = new Artist();
                artist.setId(id);
                artist.setName(name);
                return artist;
            }
        }
        return null;
    }
}

