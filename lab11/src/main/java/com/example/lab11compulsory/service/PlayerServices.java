package com.example.lab11compulsory.service;

import com.example.lab11compulsory.entity.Player;
import com.example.lab11compulsory.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServices
{
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServices(PlayerRepository playerRepository)
    {
        this.playerRepository=playerRepository;
    }

    public List<Player> getAllPlayers()
    {
        return playerRepository.findAll();
    }
}
