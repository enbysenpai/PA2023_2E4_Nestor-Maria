package com.example.lab9_v2.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="albums")
@NamedQueries({
        @NamedQuery(name = "Album.findByName",
                query = "SELECT a FROM Album a WHERE a.title LIKE :titlePattern"),

        @NamedQuery(name="Album.findByReleaseYear",
                query="SELECT a FROM Album a WHERE a.releaseYear=:releaseYear"),

        @NamedQuery(name="Album.findByArtistId",
        query = "SELECT a from Album a JOIN a.artist ar WHERE ar.id=:artistId")
})
public class Album
{
    @Id
    @Column(name="id")
    private int id;

    @Column(name="release_year")
    private int releaseYear;

    @Column(name="title")
    private String title;

    @ManyToMany
    @JoinTable(name="album_genres",
            joinColumns = @JoinColumn(name="album_id"),
            inverseJoinColumns = @JoinColumn(name="genre_id"))
    private List<Genre> genres=new ArrayList<>();

    public void addGenre(Genre genre)
    {
        this.genres.add(genre);
    }
    @ManyToOne
    private Artist artist;

    public void setArtist(Artist artist)
    {
        this.artist=artist;
    }


    public Album(){}

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

//    public void setArtistId(int artistId) {
//        this.artistId = artistId;
//    }
//
//    public int getArtistId() {
//        return artistId;
//    }

    @Override
    public String toString()
    {
        return "Album[id="
                +id+", release year="
                +releaseYear+", title="
                +title+", artist id="
                /*+artistId*/+"]";
    }
}