package com.example.lab11optional.repository;

import com.example.lab11optional.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Integer>
{

}
