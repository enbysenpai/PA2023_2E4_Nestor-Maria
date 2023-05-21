package com.example.lab11optional.entity;


import jakarta.persistence.*;


@Entity
@Table(name="players")
public class Player
{
    private Integer id;
    private Integer gameId;
    private String name;

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
    @Column(name="game_id")

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    @Basic
    @Column(name="name")

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
