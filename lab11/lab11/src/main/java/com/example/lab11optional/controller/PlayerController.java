package com.example.lab11optional.controller;

import com.example.lab11optional.entity.Player;
import com.example.lab11optional.service.PlayerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/players")
    public Player createPlayer(@RequestBody Player player)
    {
        return playerServices.createPlayer(player);
    }

    @PutMapping("/players/{id}")
    public Player modifyPlayer(@PathVariable Integer id,@RequestBody Player player)
    {
        player.setId(id);
        return playerServices.modifyPlayer(player);
    }

    @DeleteMapping("/players/{id}")
    public void deletePlayer(@PathVariable Integer id)
    {
        playerServices.deletePlayer(id);
    }
}
