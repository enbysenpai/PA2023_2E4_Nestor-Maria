package org.example.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;


@NoArgsConstructor
@Getter
@Setter
@ToString
public class Playlist
{
    private int id;
    private String name;
    private Timestamp creationTimestamp;
}
