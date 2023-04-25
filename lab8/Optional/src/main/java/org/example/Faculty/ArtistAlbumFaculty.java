package org.example.Faculty;

import org.example.DB;
import org.example.Model.Album;
import org.example.Model.Artist;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArtistAlbumFaculty {
    public static List<Album> getAlbums(int artistId) throws SQLException {
        Connection con = DB.getConnection();
        List<Album> albums = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM albums WHERE id_artist IN (SELECT id FROM artists WHERE id='" + artistId + "')")) {

            while (rs.next()) {
                int albumId = rs.getInt("id");
                int releaseYear = rs.getInt("release_year");
                String title = rs.getString("title");
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

    public static Artist getArtist(int albumId) throws SQLException {
        Connection con = DB.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM artists WHERE id IN (SELECT id_artist FROM albums WHERE id='" + albumId + "')")) {
            while (rs.next()) {
                int id = rs.getInt("id");
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
