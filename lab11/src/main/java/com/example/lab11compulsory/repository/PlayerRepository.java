package com.example.lab11compulsory.repository;

import com.example.lab11compulsory.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player,Integer>
{

}
