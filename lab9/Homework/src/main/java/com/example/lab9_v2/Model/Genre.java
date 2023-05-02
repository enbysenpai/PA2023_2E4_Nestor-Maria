package com.example.lab9_v2.Model;

import jakarta.persistence.*;

@Entity
@Table(name="genres")
@NamedQueries({
        @NamedQuery(name="Genre.findByName",
        query = "SELECT g FROM Genre g WHERE g.name LIKE :namePattern")
})
public class Genre
{
    @Id
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    public Genre(){}

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
        return "Genre[id="
                +id+", name="
                +name+"]";
    }
}
