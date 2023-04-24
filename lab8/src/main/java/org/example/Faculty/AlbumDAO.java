package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO
{
    public static void create(Album album) throws SQLException
    {
        Connection con=Database.getConnection();
        try(PreparedStatement pstmt=con.prepareStatement("INSERT INTO albums (id,release_year,title,id_artist) VALUES (?,?,?,?)"))
        {
            pstmt.setInt(1,album.getId());
            pstmt.setInt(2,album.getReleaseYear());
            pstmt.setString(3,album.getTitle());
            pstmt.setInt(4,album.getArtistId());

            pstmt.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static List<Album> findByTitle(String title) throws SQLException
    {
        Connection con=Database.getConnection();
        List<Album>albums=new ArrayList<>();
        try(Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT id,release_year,title,id_artist FROM albums WHERE title='"+title+"'"))
        {
            while(rs.next())
            {
                int id=rs.getInt("id");
                int releaseYear=rs.getInt("release_year");
                int artistId=rs.getInt("id_artist");
                albums.add(new Album(id,releaseYear,title,artistId));
            }
        }
        return albums;
    }

    public static List<Album> findById(int id) throws SQLException
    {
        Connection con=Database.getConnection();
        List<Album> albums=new ArrayList<>();
        try(Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT id,release_year,title,id_artist FROM albums WHERE id='"+id+"'"))
        {
            while(rs.next())
            {
                String title=rs.getString("title");
                int releaseYear=rs.getInt("release_year");
                int artistId=rs.getInt("id_artist");
                albums.add(new Album(id,releaseYear,title,artistId));
            }
        }
        return albums;
    }

    public static List<Album> findByReleaseYear(int releaseYear) throws SQLException
    {
        Connection con=Database.getConnection();
        List<Album> albums=new ArrayList<>();
        try(Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT id,release_year,title,id_artist FROM albums WHERE release_year='"+releaseYear+"'"))
        {
            while(rs.next())
            {
                int id=rs.getInt("id");
                String title=rs.getString("title");
                int artistId=rs.getInt("id_artist");
                albums.add(new Album(id,releaseYear,title,artistId));
            }
        }
        return albums;
    }

    public static List<Album> findByArtistId(int artistId) throws SQLException
    {
        Connection con=Database.getConnection();
        List<Album> albums=new ArrayList<>();
        try(Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT id,release_year,title,id_artist FROM albums WHERE id_artist='"+artistId+"'"))
        {
            while(rs.next())
            {
                int id=rs.getInt("id");
                String title=rs.getString("title");
                int releaseYear=rs.getInt("release_year");
                albums.add(new Album(id,releaseYear,title,artistId));
            }
        }
        return albums;
    }
}
