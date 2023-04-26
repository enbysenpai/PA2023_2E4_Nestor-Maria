package org.example.Faculty;

import org.example.DB;
import org.example.Model.Album;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumFaculty {
    public static boolean create(Album album) throws SQLException {

        Connection con = DB.getConnection();
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            pstmt = con.prepareStatement("SELECT id FROM albums WHERE title=?");
            pstmt.setString(1, album.getTitle());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                System.err.println("THIS ALBUM EXISTS: ID=" + id);
                return false;
            } else {
                pstmt = con.prepareStatement("SELECT COUNT(*) FROM albums");
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    album.setId(rs.getInt("COUNT(*)") + 1);
                }

                pstmt = con.prepareStatement("INSERT INTO albums (id,release_year,title,id_artist) VALUES (?,?,?,?)");
                pstmt.setInt(1, album.getId());
                pstmt.setInt(2, album.getReleaseYear());
                pstmt.setString(3, album.getTitle());
                pstmt.setInt(4, album.getArtistId());

                pstmt.executeUpdate();
                System.out.println("NEW ALBUM ADDED");
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static Album findByTitle(String title) throws SQLException {
        Connection con = DB.getConnection();


        PreparedStatement pstmt = con.prepareStatement("SELECT id,release_year,title,id_artist FROM albums WHERE title=?");
        pstmt.setString(1, title);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id");
            int releaseYear = rs.getInt("release_year");
            int artistId = rs.getInt("id_artist");
            Album album = new Album();
            album.setId(id);
            album.setTitle(title);
            album.setReleaseYear(releaseYear);
            album.setArtistId(artistId);
            return album;
        }

        return null;
    }

    public static Album findById(int id) throws SQLException {
        Connection con = DB.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id,release_year,title,id_artist FROM albums WHERE id='" + id + "'")) {
            if (rs.next()) {
                String title = rs.getString("title");
                int releaseYear = rs.getInt("release_year");
                int artistId = rs.getInt("id_artist");
                Album album = new Album();
                album.setId(id);
                album.setTitle(title);
                album.setReleaseYear(releaseYear);
                album.setArtistId(artistId);
                return album;
            }
        }
        return null;
    }

    public static List<Album> findByReleaseYear(int releaseYear) throws SQLException {
        Connection con = DB.getConnection();
        List<Album> albums = new ArrayList<>();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id,release_year,title,id_artist FROM albums WHERE release_year='" + releaseYear + "'")) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int artistId = rs.getInt("id_artist");
                Album album = new Album();
                album.setId(id);
                album.setTitle(title);
                album.setReleaseYear(releaseYear);
                album.setArtistId(artistId);
                albums.add(album);
            }
        }
        return albums;
    }

    public static List<Album> findByArtistId(int artistId) throws SQLException {
        Connection con = DB.getConnection();
        List<Album> albums = new ArrayList<>();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id,release_year,title,id_artist FROM albums WHERE id_artist='" + artistId + "'")) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int releaseYear = rs.getInt("release_year");
                Album album = new Album();
                album.setId(id);
                album.setTitle(title);
                album.setReleaseYear(releaseYear);
                album.setArtistId(artistId);
                albums.add(album);
            }
        }
        return albums;
    }

    public static List<Album> showAll() throws SQLException
    {
        Connection con=DB.getConnection();
        List<Album>albums=new ArrayList<>();
        try(Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM albums"))
        {
            while(rs.next()) {
                int id = rs.getInt("id");
                int releaseYear = rs.getInt("release_year");
                String title = rs.getString("title");
                int artistId = rs.getInt("id_artist");
                Album album = new Album();
                album.setId(id);
                album.setReleaseYear(releaseYear);
                album.setTitle(title);
                album.setArtistId(artistId);
                albums.add(album);
            }
        }
        return albums;
    }
}
