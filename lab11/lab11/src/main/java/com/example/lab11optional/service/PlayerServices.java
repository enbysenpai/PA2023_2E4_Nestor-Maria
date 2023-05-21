package com.example.lab11optional.service;

import com.example.lab11optional.entity.Player;
import com.example.lab11optional.repository.PlayerRepository;
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

    public Player createPlayer(Player player)
    {
        return playerRepository.save(player);
    }

    public Player modifyPlayer(Player player)
    {
        return playerRepository.save(player);
    }

    public void deletePlayer(Integer id)
    {
        playerRepository.delete(playerRepository.getReferenceById(id));
    }
}
