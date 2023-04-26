package org.example;

import org.example.Faculty.*;
import org.example.Model.Artist;
import org.example.Model.Genre;
import org.example.Model.Playlist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        try {
            Connection connection = DB.getConnection();

//            DataReader.readFile(connection);
//            DataReader.readFromFile();

            Playlist playlist=new Playlist();
            playlist.setName("Study Playlist");
            PlaylistsFaculty.create(playlist);

            PlaylistAlbumsFaculty.create(1,AlbumFaculty.findByTitle("Bleach").getId());

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
