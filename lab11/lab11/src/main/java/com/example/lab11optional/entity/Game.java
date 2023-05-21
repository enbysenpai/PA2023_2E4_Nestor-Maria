package com.example.lab11optional.entity;


import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name="games")
public class Game
{
    private Integer id;
    private Date date;

    @Id
    @GeneratedValue
    @Column(name="id")

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name="date")

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
