package org.example;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAO
{
    public static void create(Artist artist) throws SQLException
    {
        Connection con=Database.getConnection();
        try(PreparedStatement pstmt=con.prepareStatement("INSERT INTO artists (id,name) VALUES (?,?)"))
        {
            pstmt.setInt(1,artist.getId());
            pstmt.setString(2,artist.getName());

            pstmt.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static List<Artist> findByName(String name) throws SQLException
    {
        Connection con=Database.getConnection();
        List<Artist>artists=new ArrayList<>();
        try(Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT id,name FROM artists WHERE name='"+name+"'"))
        {
            while(rs.next())
            {
                int id=rs.getInt("id");
                artists.add(new Artist(id,name));
            }
        }
        return artists;
    }

    public static List<Artist> findById(int id) throws SQLException
    {
        Connection con=Database.getConnection();
        List<Artist>artists=new ArrayList<>();
        try(Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT id,name FROM artists WHERE id='"+id+"'"))
        {
            while(rs.next())
            {
                String name=rs.getString("name");
                artists.add(new Artist(id,name));
            }
        }
        return artists;
    }
}
