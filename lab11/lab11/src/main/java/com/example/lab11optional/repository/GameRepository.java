package com.example.lab11optional.repository;

import com.example.lab11optional.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game,Integer>
{

}
