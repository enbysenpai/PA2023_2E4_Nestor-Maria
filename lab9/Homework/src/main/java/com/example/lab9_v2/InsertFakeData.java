package com.example.lab9_v2;

import com.example.lab9_v2.Model.Album;
import com.example.lab9_v2.Model.Artist;
import com.example.lab9_v2.Repository.AlbumRepository;
import com.example.lab9_v2.Repository.ArtistRepository;
import com.github.javafaker.Faker;
import jakarta.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;

public class InsertFakeData
{
    private EntityManagerFactory entityManagerFactory;

    public static void insertData(EntityManagerFactory entityManagerFactory)
    {
        ArtistRepository artistRepository=new ArtistRepository(entityManagerFactory);
        AlbumRepository albumRepository=new AlbumRepository(entityManagerFactory);

        Faker faker=new Faker();

        //start timer to log the execution time
        long startTime=System.currentTimeMillis();

        Artist newArtist=new Artist();
        for(int i=0;i<500;i++)
        {
            String name=faker.name().fullName();
            newArtist.setName(name);
            artistRepository.save(newArtist);
        }

        Album newAlbum=new Album();
        for(int i=0;i<1000;i++)
        {
            String title=faker.book().title();
            int releaseYear=faker.number().numberBetween(1920,2023);
            int artistId=faker.number().numberBetween(0,792);
            newAlbum.setTitle(title);
            newAlbum.setReleaseYear(releaseYear);
            newAlbum.setArtistId(artistId);
            albumRepository.save(newAlbum);
        }

        long endTime=System.currentTimeMillis();
        System.out.println("EXECUTION TIME FOR INSERTING FAKE DATA: "+(endTime-startTime));
    }
}
