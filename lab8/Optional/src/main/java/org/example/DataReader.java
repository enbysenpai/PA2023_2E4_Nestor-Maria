package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.example.Faculty.AlbumFaculty;
import org.example.Faculty.AlbumGenresFaculty;
import org.example.Faculty.ArtistFaculty;
import org.example.Faculty.GenresFaculty;
import org.example.Model.Album;
import org.example.Model.Artist;
import org.example.Model.Genre;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    private static String path = "src/main/java/org/example/albumlist.csv";
    private static int maxNumber = 100;
    private static CSVReader reader;


    private DataReader() {
    }

    public static void readFile(Connection connection) {
        try {
            reader = new CSVReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void readFromFile() {
        try {
            String[] line = reader.readNext();
            line = reader.readNext();


            while (line != null) {
                int releaseYear = Integer.parseInt(line[1]);
                String albumTitle = line[2];
                String artistName = line[3];

                String[] subgenres = line[5].split(",");

                Artist artist = new Artist();
                artist.setName(artistName);
                if (!ArtistFaculty.create(artist))
                    artist = ArtistFaculty.findByName(artistName);
                int artistId = artist.getId();

                Album album = new Album();
                album.setTitle(albumTitle);
                album.setReleaseYear(releaseYear);
                album.setArtistId(artistId);
                if (!AlbumFaculty.create(album))
                    album = AlbumFaculty.findByTitle(albumTitle);
                int albumId = album.getId();

                String[] genres = line[4].split(", ");
                for (int i = 0; i < genres.length; i++) {
                    if (genres[i].startsWith("& "))
                        genres[i] = genres[i].substring(2);

                    Genre genre = new Genre();
                    genre.setName(genres[i]);
                    if (!GenresFaculty.create(genre))
                        genre = GenresFaculty.findByName(genres[i]);
                    int genreId = genre.getId();
                    AlbumGenresFaculty.create(albumId, genreId);
                }

                String[] subgenre = line[5].split(", ");
                for (int i = 0; i < subgenre.length; i++) {
                    Genre genre = new Genre();
                    genre.setName(subgenre[i]);
                    if (!GenresFaculty.create(genre))
                        genre = GenresFaculty.findByName(subgenre[i]);
                    int genreId = genre.getId();
                    AlbumGenresFaculty.create(albumId, genreId);
                }

                line = reader.readNext();
            }
        } catch (IOException | SQLException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}