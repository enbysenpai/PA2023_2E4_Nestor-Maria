package org.example.Faculty;

import org.example.DB;
import org.example.Model.Album;
import org.example.Model.Playlist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistAlbumsFaculty
{
    public static boolean create(int playlistId,int albumId)throws SQLException
    {
        Connection con= DB.getConnection();
        PreparedStatement pstmt;
        ResultSet rs;

        try
        {
            pstmt=con.prepareStatement("SELECT playlist_id,album_id FROM playlist_albums WHERE playlist_id=? AND album_id=?");
            pstmt.setInt(1,playlistId);
            pstmt.setInt(2,albumId);
            rs= pstmt.executeQuery();
            if(rs.next())
            {
                System.err.println("THE ALBUM ALREADY EXISTS IN THIS PLAYLIST");
                return false;
            }
            else
            {
                pstmt=con.prepareStatement("INSERT INTO playlist_albums (playlist_id,album_id) VALUES (?,?)");
                pstmt.setInt(1,playlistId);
                pstmt.setInt(2,albumId);
                pstmt.executeUpdate();
                System.out.println("A NEW ALBUM WAS ADDED TO THE CURRENT PLAYLIST");
            }
        }
        catch(SQLIntegrityConstraintViolationException e)
        {
            e.printStackTrace();
        }
        return true;
    }

    public static List<Album> getAlbums(int playlistId) throws SQLException {
        Connection con = DB.getConnection();
        List<Album> albums = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM albums WHERE id IN (SELECT album_id FROM playlist_albums WHERE playlist_id='" + playlistId + "')")) {

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

    public static List<Playlist> getPlaylists(int albumId) throws SQLException
    {
        Connection con=DB.getConnection();
        List<Playlist>playlists=new ArrayList<>();
        try(Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM playlists WHERE id IN (SELECT playlist_id FROM playlist_albums WHERE album_id='"+albumId+"'"))
        {
            while(rs.next())
            {
                int playlistId=rs.getInt("id");
                String name=rs.getString("name");
                Timestamp creationTimestamp=rs.getTimestamp("creation_timestamp");
                Playlist playlist=new Playlist();
                playlist.setId(playlistId);
                playlist.setName(name);
                playlist.setCreationTimestamp(creationTimestamp);
                playlists.add(playlist);
            }
        }
        return playlists;
    }
}
