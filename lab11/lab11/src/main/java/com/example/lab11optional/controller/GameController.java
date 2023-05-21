package com.example.lab11optional.controller;

import com.example.lab11optional.entity.Game;
import com.example.lab11optional.service.GameServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GameController
{
    private final GameServices gameServices;

    @Autowired
    public GameController(GameServices gameServices)
    {
        this.gameServices=gameServices;
    }

    @GetMapping("/games")
    public List<Game> getAllGames()
    {
        return gameServices.getAllGames();
    }
}
