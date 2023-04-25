package org.example.Faculty;

import org.example.DB;
import org.example.Model.Album;
import org.example.Model.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumGenresFaculty {
    public static boolean create(int albumId, int genreId) throws SQLException {

        Connection con = DB.getConnection();
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            pstmt = con.prepareStatement("SELECT album_id,genre_id FROM album_genres WHERE album_id=? AND genre_id=?");
            pstmt.setInt(1, albumId);
            pstmt.setInt(2, genreId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                System.err.println("THIS ALBUM-GENRE RELATIONSHIP EXISTS");
                return false;
            } else {
                pstmt = con.prepareStatement("INSERT INTO album_genres (album_id,genre_id) VALUES (?,?)");
                pstmt.setInt(1, albumId);
                pstmt.setInt(2, genreId);

                pstmt.executeUpdate();
                System.out.println("NEW ALBUM-GENRE RELATIONSHIP ADDED");
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static List<Album> getAlbums(int genreId) throws SQLException {
        Connection con = DB.getConnection();
        List<Album> albums = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM albums WHERE id IN (SELECT album_id FROM album_genres WHERE genre_id='" + genreId + "')")) {

            while (rs.next()) {
                int albumId = rs.getInt("id");
                int releaseYear = rs.getInt("release_year");
                String title = rs.getString("title");
                int artistId = rs.getInt("id_artist");
                Album album = new Album();
                album.setId(albumId);
                album.setReleaseYear(releaseYear);
                album.setTitle(title);
                album.setArtistId(artistId);
                albums.add(album);
            }
        }
        return albums;
    }

    public static List<Genre> getGenres(int albumId) throws SQLException {
        Connection con = DB.getConnection();
        List<Genre> genres = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM genres WHERE id IN (SELECT genre_id FROM album_genres WHERE album_id='" + albumId + "')")) {

            while (rs.next()) {
                int genreId = rs.getInt("id");
                String name = rs.getString("name");
                Genre genre = new Genre();
                genre.setName(name);
                genre.setId(genreId);
                genres.add(genre);
            }
        }
        return genres;
    }
}

