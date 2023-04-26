package org.example.Faculty;

import org.example.DB;
import org.example.Model.Artist;
import org.example.Model.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistGenreFaculty {
    public static List<Genre> getArtistsGenresById(int artistId) throws SQLException {
        Connection con = DB.getConnection();
        List<Genre> genres = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("SELECT DISTINCT g.id,g.name FROM genres g JOIN album_genres ON g.id=genre_id JOIN albums a ON album_id=a.id WHERE id_artist='" + artistId + "'")) {
            while (rs.next()) {
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

    public static List<Genre> getArtistsGenresByName(String artistName) throws SQLException {
        Connection con = DB.getConnection();
        List<Genre> genres = new ArrayList<>();
        PreparedStatement pstmt = con.prepareStatement("SELECT DISTINCT g.id,g.name FROM genres g JOIN album_genres ON g.id=genre_id JOIN albums a ON album_id=a.id JOIN artists ar ON ar.id=id_artist WHERE ar.name=?");
        pstmt.setString(1, artistName);
        ResultSet rs = pstmt.executeQuery();
        {
            while (rs.next()) {
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

    public static List<Artist> getGenresArtistsById(int genreId) throws SQLException {
        Connection con = DB.getConnection();
        List<Artist> artists = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("SELECT DISTINCT ar.id,ar.name FROM artists ar JOIN albums a ON a.id_artist=ar.id JOIN album_genres ag ON ag.album_id=a.id JOIN genres g ON g.id=ag.genre_id WHERE g.id=" + genreId)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Artist artist = new Artist();
                artist.setId(id);
                artist.setName(name);
                artists.add(artist);
            }
        }
        return artists;
    }

    public static List<Artist> getGenresArtistsByName(String genreName) throws SQLException {
        Connection con = DB.getConnection();
        List<Artist> artists = new ArrayList<>();
        PreparedStatement pstmt = con.prepareStatement("SELECT DISTINCT ar.id,ar.name FROM artists ar JOIN albums a ON a.id_artist=ar.id JOIN album_genres ag ON ag.album_id=a.id JOIN genres g ON g.id=ag.genre_id WHERE g.name=?");
        pstmt.setString(1, genreName);
        ResultSet rs = pstmt.executeQuery();
        {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Artist artist = new Artist();
                artist.setId(id);
                artist.setName(name);
                artists.add(artist);
            }
        }
        return artists;
    }
}
