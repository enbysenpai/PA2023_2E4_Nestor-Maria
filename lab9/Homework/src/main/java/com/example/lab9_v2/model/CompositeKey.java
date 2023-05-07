package com.example.lab9_v2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CompositeKey implements Serializable
{
    @Column(name="album_id")
    private int albumId;

    @Column(name="genre_id")
    private int genreId;

    public CompositeKey(){}

    public int getAlbumId()
    {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getGenreId()
    {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    @Override
    public String toString()
    {
        return "album id="+albumId
                +", genre id="+genreId;
    }
}
