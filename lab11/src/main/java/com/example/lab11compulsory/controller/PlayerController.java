package com.example.lab11compulsory.controller;

import com.example.lab11compulsory.entity.Player;
import com.example.lab11compulsory.service.PlayerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PlayerController
{
    private final PlayerServices playerServices;

    @Autowired
    public PlayerController(PlayerServices playerServices)
    {
        this.playerServices=playerServices;
    }

    @GetMapping("/players")
    public List<Player> getAllPlayers()
    {
        return playerServices.getAllPlayers();
    }
}
