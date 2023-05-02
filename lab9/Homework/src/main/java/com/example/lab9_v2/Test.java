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
//        artist.setName("TEST");
//        artistRepository.save(artist);
//        artistRepository.deleteById(artistRepository.findByNameSpecific(artist.getName()).getId());
//        System.out.println(artistRepository.findByNamePattern("Jane"));
//        artist=artistRepository.findByNameSpecific(artist.getName());
//        artist.setName("TEST_UPDATE");
//        System.out.println(artistRepository.findByNameSpecific(artist.getName()));
//        artistRepository.update(artist);
//        System.out.println(artistRepository.findByNameSpecific(artist.getName()));
//        List<Artist> artists=artistRepository.findAll();
//        for(Artist artistI:artists)
//        {
//            System.out.println(artistI);
//        }


//        AlbumRepository albumRepository=new AlbumRepository(emf);
//        Album album=new Album();
//        album.setTitle("TEST");
//        albumRepository.save(album);
//        albumRepository.deleteById(albumRepository.findByNameSpecific(album.getTitle()).getId());
//        System.out.println(albumRepository.findByNamePattern("Live"));
//        album=albumRepository.findByNameSpecific(album.getTitle());
//        album.setTitle("TEST_UPDATE");
//        System.out.println(albumRepository.findByNameSpecific(album.getTitle()));
//        albumRepository.update(album);
//        System.out.println(albumRepository.findByNameSpecific(album.getTitle()));
//        List<Album> albums=albumRepository.findAll();
//        for(Album albumI:albums)
//        {
//            System.out.println(albumI);
//        }
//        System.out.println(albumRepository.findByReleaseYear(2002));
//        System.out.println(albumRepository.findByArtistId(3));

//        GenreRepository genreRepository=new GenreRepository(emf);
//        Genre genre=new Genre();
//        genre.setName("TEST");
//        genreRepository.save(genre);
//        genreRepository.deleteById(genreRepository.findByNameSpecific(genre.getName()).getId());
//        System.out.println(genreRepository.findByNamePattern("Rock"));
//        genre=genreRepository.findByNameSpecific(genre.getName());
//        genre.setName("TEST_UPDATE");
//        System.out.println(genreRepository.findByNameSpecific(genre.getName()));
//        genreRepository.update(genre);
//        System.out.println(genreRepository.findByNameSpecific(genre.getName()));
//        List<Genre> genres=genreRepository.findAll();
//        for(Genre genreI:genres)
//        {
//            System.out.println(genreI);
//        }

        InsertFakeData insertFakeData=new InsertFakeData();
        insertFakeData.insertData(emf);

        EMFManager.closeEntityManagerFactory();
    }
}
