package com.example.lab9_v2.Model;

import jakarta.persistence.*;

@Entity
@Table(name="artists")
@NamedQueries({
        @NamedQuery(name = "Artist.findByName",
                query = "SELECT a FROM Artist a WHERE a.name LIKE :namePattern")
})
public class Artist
{
    @Id
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    public Artist(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "Artist[id="
                +id+", name="
                +name+"]";
    }
}
