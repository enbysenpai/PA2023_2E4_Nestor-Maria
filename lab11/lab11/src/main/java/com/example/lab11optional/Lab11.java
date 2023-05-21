package com.example.lab11optional;

import com.example.lab11optional.entity.Player;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Lab11 {

    public static void main(String[] args)
    {
        SpringApplication.run(Lab11.class, args);

        //player related:
        RestTemplate restTemplate=new RestTemplate();
        String url="http://localhost:8080/api/players";

        //get the list of registered players
        ResponseEntity<String> playersResponse=restTemplate.getForEntity(url,String.class);
        System.out.println("Registered players: "+playersResponse);

        //add a new player
        Player player=new Player();
        player.setGameId(1000);
        player.setName("John Doe");
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Player> request=new HttpEntity<>(player,headers);
        ResponseEntity<String>addPlayerResponse=restTemplate.postForEntity(url,request,String.class);
        System.out.println("New player added: "+addPlayerResponse.getBody());


        //Modify the name of a player
        Player modifiedPlayer=new Player();
        modifiedPlayer.setName("Jane Doe");
        modifiedPlayer.setGameId(player.getGameId());
        Integer playerId=52;
        HttpEntity<Player>updateRequest=new HttpEntity<>(modifiedPlayer,headers);
        restTemplate.exchange(url+"/"+playerId, HttpMethod.PUT,updateRequest,Void.class);
        System.out.println("Modified player's name successfully");

        //Delete a player
        playerId=102;
        restTemplate.delete(url+"/"+playerId);
        System.out.println("Deleted player successfully");

        //Games related:
        url="http://localhost:8080/api/games";

        //Get list of games
        ResponseEntity<String> responseEntity=restTemplate.getForEntity(url,String.class);
        System.out.println("Games: "+responseEntity.getBody());
    }

}
