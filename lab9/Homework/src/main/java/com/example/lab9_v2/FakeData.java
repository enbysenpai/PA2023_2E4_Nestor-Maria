package com.example.lab9_v2;

import com.example.lab9_v2.model.Album;
import com.example.lab9_v2.model.Artist;
import com.example.lab9_v2.repository.AlbumRepository;
import com.example.lab9_v2.repository.ArtistRepository;
import com.github.javafaker.Faker;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.Random;

public class FakeData {
    private EntityManagerFactory entityManagerFactory;

    public static void insertData(EntityManagerFactory entityManagerFactory) {
        ArtistRepository artistRepository = new ArtistRepository(entityManagerFactory);
        AlbumRepository albumRepository = new AlbumRepository(entityManagerFactory);
        Faker faker = new Faker();

        //start timer to log the execution time
        long startTime = System.currentTimeMillis();

        Artist newArtist = new Artist();
        for (int i = 0; i < 500; i++) {
            String name = faker.name().fullName();
            newArtist.setName(name);
            artistRepository.save(newArtist);
        }
        Album newAlbum = new Album();
        String title;
        int releaseYear;
        for (int i = 0; i < 1000; i++) {
            title = faker.book().title();
            releaseYear = faker.number().numberBetween(1920, 2023);

            newAlbum.setTitle(title);
            newAlbum.setReleaseYear(releaseYear);
//            newAlbum.setArtist(artistRepository.findAll().get(new Random().nextInt(artistRepository.findAll().size())));
            albumRepository.save(newAlbum);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("EXECUTION TIME FOR INSERTING FAKE DATA: " + (endTime - startTime));
    }
}

