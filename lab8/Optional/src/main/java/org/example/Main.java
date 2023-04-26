package org.example;

import org.example.Faculty.*;
import org.example.Model.Artist;
import org.example.Model.Genre;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        try {
            Connection connection = DB.getConnection();

            //DataReader.readFile(connection);
            //DataReader.readFromFile();

            System.out.println(AlbumFaculty.showAll());

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
