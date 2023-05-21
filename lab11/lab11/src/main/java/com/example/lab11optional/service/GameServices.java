package com.example.lab11optional.service;

import com.example.lab11optional.entity.Game;
import com.example.lab11optional.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServices
{
    private final GameRepository gameRepository;

    @Autowired
    public GameServices(GameRepository gameRepository)
    {
        this.gameRepository=gameRepository;
    }

    public List<Game> getAllGames()
    {
        return gameRepository.findAll();
    }

}
