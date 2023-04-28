package org.example.Faculty;

import org.example.DB;
import org.example.Model.Album;
import org.example.Model.Genre;
import org.example.Model.Playlist;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.Subgraph;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class PlaylistsFaculty {
    public static boolean create(Playlist playlist) throws SQLException {
        Connection con = DB.getConnection();
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            pstmt = con.prepareStatement("SELECT id FROM playlists WHERE name=?");
            pstmt.setString(1, playlist.getName());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                System.err.println("YOU ALREADY HAVE A PLAYLIST USING THIS NAME: ID=" + id);
                return false;
            } else {
                pstmt = con.prepareStatement("SELECT COUNT(*) FROM playlists");
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    playlist.setId(rs.getInt("COUNT(*)") + 1);
                }

                Date date = new Date();
                Timestamp timestamp = new Timestamp(date.getTime());
                playlist.setCreationTimestamp(timestamp);
                pstmt = con.prepareStatement("INSERT INTO playlists (id,name,creation_timestamp) VALUES (?,?,?)");
                pstmt.setInt(1, playlist.getId());
                pstmt.setString(2, playlist.getName());
                pstmt.setTimestamp(3, playlist.getCreationTimestamp());

                pstmt.executeUpdate();
                System.out.println("PLAYLIST ADDED");
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static Playlist findById(int id) throws SQLException {
        Connection con = DB.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id,name,creation_timestamp FROM playlists WHERE id=" + id)) {
            if (rs.next()) {
                String name = rs.getString("name");
                Timestamp creationTimestamp = rs.getTimestamp("creation_timestamp");
                Playlist playlist = new Playlist();
                playlist.setId(id);
                playlist.setName(name);
                playlist.setCreationTimestamp(creationTimestamp);
                return playlist;
            }
        }
        return null;
    }

    public static Playlist findByTitle(String name) throws SQLException {
        Connection con = DB.getConnection();


        PreparedStatement pstmt = con.prepareStatement("SELECT id,name,creation_timestamp FROM playlists WHERE name=?");
        pstmt.setString(1, name);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id");
            Timestamp creationTimestamp = rs.getTimestamp("creation_timestamp");
            Playlist playlist = new Playlist();
            playlist.setId(id);
            playlist.setName(name);
            playlist.setCreationTimestamp(creationTimestamp);
            return playlist;
        }

        return null;
    }

    public static List<Playlist> findByCreationTimestamp(Timestamp timestamp) throws SQLException {
        Connection con = DB.getConnection();
        List<Playlist> playlists = new ArrayList<>();

        PreparedStatement pstmt = con.prepareStatement("SELECT id,name,creation_timestamp FROM playlist WHERE creation_timestamp=?");
        pstmt.setTimestamp(1, timestamp);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            Playlist playlist = new Playlist();
            playlist.setId(id);
            playlist.setName(name);
            playlist.setCreationTimestamp(timestamp);
            playlists.add(playlist);
        }

        return playlists;
    }

    public static List<Playlist> showAll() throws SQLException {
        Connection con = DB.getConnection();
        List<Playlist> playlists = new ArrayList<>();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM playlists")) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Timestamp timestamp = rs.getTimestamp("creation_timestamp");
                Playlist playlist = new Playlist();
                playlist.setId(id);
                playlist.setName(name);
                playlist.setCreationTimestamp(timestamp);
                playlists.add(playlist);
            }
        }
        return playlists;
    }


    public static void generateMaximalPlaylists() throws SQLException
    {
        //All the albums from the database
        List<Album> albums=AlbumFaculty.showAll();

        //Sort them in descending order (by release year)
        //Why in descending order? Why not lmao
        Collections.sort(albums,Collections.reverseOrder());


        List<Album> maximalPlaylists=new ArrayList<>();

        //Keep track of used criteria
        HashSet<Integer> usedYears=new HashSet<>();
        HashSet<Integer> usedArtists=new HashSet<>();
        HashSet<Integer> usedGenres=new HashSet<>();


        //Iterate over each album & try to create a maximal playlist
        for(Album album:albums)
        {
            //Check if the year was used
            if(usedYears.contains(album.getReleaseYear()))
                continue;

            //Check if artist was used
            if(usedArtists.contains(album.getArtistId()))
                continue;

            //Check if there exists any common genre
            List<Genre>genres=AlbumGenresFaculty.getGenres(album.getId());

            if(!Collections.disjoint(usedGenres,genres))
                continue;

            maximalPlaylists.add(album);
            usedYears.add(album.getReleaseYear());
            usedArtists.add(album.getArtistId());
            for(Genre genre:genres)
                usedGenres.add(genre.getId());
        }

        Playlist playlist=new Playlist();
        playlist.setName("Maximal Playlist");
        create(playlist);

        for(Album album:maximalPlaylists)
        {
            PlaylistAlbumsFaculty.create(playlist.getId(),album.getId());
        }
    }

}
