package com.example.lab9_v2;


import com.example.lab9_v2.Manager.EMFManager;
import com.example.lab9_v2.Model.Album;
import com.example.lab9_v2.Model.Artist;
import com.example.lab9_v2.Model.Genre;
import com.example.lab9_v2.Repository.AlbumRepository;
import com.example.lab9_v2.Repository.ArtistRepository;
import com.example.lab9_v2.Repository.GenreRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.hibernate.sql.ast.spi.SqlAliasBaseManager;

import java.util.List;

public class Test
{
    public static void testJPA()
    {
        EntityManagerFactory emf= EMFManager.getEntityManagerFactory();

//        ArtistRepository artistRepository=new ArtistRepository(emf);
//        Artist artist=new Artist();
//        artist.setId(293);
//        artist.setName("TEST");
//        artistRepository.create(artist);
//        System.out.println(artistRepository.findById(2));
//        System.out.println(artistRepository.findById(300));
//        System.out.println(artistRepository.findByName("James"));


//        AlbumRepository albumRepository=new AlbumRepository(emf);
//        Album album=new Album();
//        album.setReleaseYear(2023);
//        album.setTitle("TEST");
//        album.setArtistId(293);
//        albumRepository.create(album);
//        System.out.println(albumRepository.findById(5));
//        System.out.println(albumRepository.findById(600));
//        System.out.println(albumRepository.findByName("Live"));

        GenreRepository genreRepository=new GenreRepository(emf);
//        Genre genre=new Genre();
//        genre.setName("TEST");
//        genreRepository.create(genre);
//        System.out.println(genreRepository.findById(8));
//        System.out.println(genreRepository.findById(200));
//        System.out.println(genreRepository.findByName("Rock"));
        EMFManager.closeEntityManagerFactory();
    }
}
