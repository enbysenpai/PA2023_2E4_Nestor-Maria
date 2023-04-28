package org.example.Model;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString


public class Album implements Comparable<Album>{
    private int id;
    private int releaseYear;
    private String title;
    private int artistId;

    @Override
    public int compareTo(Album otherAlbum)
    {
        return Integer.compare(this.releaseYear,otherAlbum.releaseYear);
    }
}
