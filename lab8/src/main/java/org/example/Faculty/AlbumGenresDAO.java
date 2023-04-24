package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumGenresDAO {
    public static void create(int albumId, int genreId) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("INSERT INTO album_genres (album_id,genre_id) VALUES (?,?)")) {
            pstmt.setInt(1, albumId);
            pstmt.setInt(2, genreId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Album> getAlbums(int genreId) throws SQLException {
        Connection con = Database.getConnection();
        List<Album> albums = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM albums WHERE id IN (SELECT album_id FROM album_genres WHERE genre_id='"+genreId+"')")) {

            while (rs.next()) {
                int albumId = rs.getInt("id");
                int releaseYear = rs.getInt("release_year");
                String title = rs.getString("title");
                int artistId = rs.getInt("id_artist");
                albums.add(new Album(albumId, releaseYear, title, artistId));
            }
        }
        return albums;
    }

    public static List<Genre> getGenres(int albumId) throws SQLException {
        Connection con = Database.getConnection();
        List<Genre> genres = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM genres WHERE id IN (SELECT genre_id FROM album_genres WHERE album_id='"+albumId+"')")) {

            while (rs.next()) {
                int genreId = rs.getInt("id");
                String name=rs.getString("name");
                genres.add(new Genre(genreId,name));
            }
        }
        return genres;
    }
}
