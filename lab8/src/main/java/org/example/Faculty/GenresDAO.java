package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenresDAO
{
    public static void create(Genre genre) throws SQLException
    {
        Connection con=Database.getConnection();
        try(PreparedStatement pstmt=con.prepareStatement("INSERT INTO genre (id,name) VALUES (?,?)"))
        {
            pstmt.setInt(1,genre.getId());
            pstmt.setString(2,genre.getName());

            pstmt.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static List<Genre> findByName(String name) throws SQLException
    {
        Connection con=Database.getConnection();
        List<Genre>genres=new ArrayList<>();
        try(Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT id,name FROM genres WHERE name='"+name+"'"))
        {
            while(rs.next())
            {
                int id=rs.getInt("id");
                genres.add(new Genre(id,name));
            }
        }
        return genres;
    }

    public static List<Genre> findById(int id) throws SQLException
    {
        Connection con=Database.getConnection();
        List<Genre>genres=new ArrayList<>();
        try(Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT id,name FROM genres WHERE id='"+id+"'"))
        {
            while(rs.next())
            {
                String name=rs.getString("name");
                genres.add(new Genre(id,name));
            }
        }
        return genres;
    }
}
