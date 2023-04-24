package org.example;

import com.mysql.cj.MysqlConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args)
    {
        Connection connection=Database.getConnection();

        try
        {
            System.out.println(ArtistDAO.findByName("Nirvana"));
            System.out.println(ArtistDAO.findById(3));
            System.out.println(GenresDAO.findByName("Heavy Metal"));
            System.out.println(GenresDAO.findById(9));
            System.out.println(AlbumDAO.findById(6));
            System.out.println(AlbumDAO.findByReleaseYear(1995));
            System.out.println(AlbumDAO.findByTitle("Facelift"));
            System.out.println(AlbumDAO.findByArtistId(2));


            Artist artist=new Artist(4,"Meltt");
            ArtistDAO.create(artist);
            System.out.println(ArtistDAO.findById(4));

            System.out.println(AlbumGenresDAO.getGenres(5));
            System.out.println(AlbumGenresDAO.getAlbums(1));

            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}