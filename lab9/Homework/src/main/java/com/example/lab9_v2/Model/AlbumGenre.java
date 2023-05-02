package com.example.lab9_v2.Model;

import jakarta.persistence.*;

@Entity
@Table(name="album_genres")
@NamedQueries({
        @NamedQuery(name="AlbumGenre.findAll",
        query="select ag from AlbumGenre ag order by ag.compositeKey.albumId")
})
public class AlbumGenre
{
    @EmbeddedId
    private CompositeKey compositeKey;

    public AlbumGenre(){}


    @Override
    public String toString()
    {
        return "Album-Genre relation: "
                +compositeKey;
    }
}
