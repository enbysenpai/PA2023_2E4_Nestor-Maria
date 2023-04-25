package org.example.Model;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Album {
    private int id;
    private int releaseYear;
    private String title;
    private int artistId;
}
