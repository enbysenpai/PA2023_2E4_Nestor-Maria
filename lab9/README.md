COMPULSORY: <em>to be presented...</em><br>
HOMEWORK: Tema este structurata in 3 package-uri + 3 clase separate.
<ol>
  <li>
    Model Package
  </li>
  <ul>
    <li>
      Clasa Artist: Reprezinta tabela "artists" din baza de date. Are un NamedQuery ce cauta artistii dupa numele lor. Tabela contine doua coloane, id si name. Clasa contine constructorul default, gettere, settere si metoda toString.
    </li>
    <li>
      Clasa Genre: Aceasta clasa este creata la fel ca cea mentionata mai sus. Reprezinta tabela "genres" din baza de date.
    </li>
    <li>
      Clasa Albums: Reprezinta tabela "albums" din baza de date. Are 3 NamedQueries: primul cauta albumele dupa numele acestora, al doilea dupa anul lansarii, iar al treilea dupa id-ul artistului. Tabela are 4 coloane: id, release_year, titile, id_artist. Clasa contine gettere, settere, cat si metoda toString.
    </li>
    <li>
      Clasa CompositeKey: Este o clasa ce contine doar doua atribute, album_id si genre_id. Este o clasa notata cu tag-ul @Embeddable, ceea ce inseamna ca este o clasa non-entitate care este utilizată pentru a reprezenta un tip de valoare încorporat într-o entitate. Aceasta clasa va fi folosita in cadrul clasei AlbumGenre deoarece aceasta contine o cheie primara compusa. Astfel ca, folosindu-ne de aceasta clasa, putem crea aceasta cheie compusa formata din ambele coloane ale tabelei albumGenres. Aceasta are gettere, settere si metoda toString.
    </li>
    <li>
      Clasa AlbumGenre: contine un NamedQuery ce afiseaza toate relatiile album-genre, ordonate dupa id-ul artistului. Contine un obiect de tip CompositeKey. Contine metoda toString.
    </li>
  </ul>
  <li>
    Repository Package:
  </li>
  <ul>
    <li>
      Clasa AbstractRepository: Este clasa abstracta ce simplifica crearea celorlalte clase din package. Primeste doi parametri generici, T si ID, ce vor fi inlocuiti cu obiectul clasei si id-ul unui obiect de acelasi tip. In constructor vom atribui la entityManagerFactory obiectul cu acelasi nume creat o singura data, in clasa de test. Tot aici gasim metodele de save, deleteById, findById, findAll, update.
    </li>
    <li>
      Clasa ArtistRepository: In constructor apelam constructorul din clasa abstacta. Metoda save salveaza artistul in baza de date. Daca artistul se gaseste deja in baza de date, afisam eroare, iar artist primeste valoarea null. Daca query-ul nu a gasit niciun raspuns, la valoarea max(id)+1 adaugam noul artist. In final, afisam valoarea lui artist. Metoda deleteById sterge artistul DOAR DACA id-ul se gaseste in baza de date (in caz contrar, se afiseaza eroare). Metoda findById cauta un artist in functie de id-ul sau. Daca id-ul nu se gaseste in baza de date, afisam eroare. Metoda findAll returneaza toate inregistrarile din tabela artists. Metoda update actualizeaza campurile unui record specific DOAR DACA se gaseste in baza de date deja. Altfel, afisam mesajul cum ca nu exista. <br>
      Pe langa metodele Override din clasa abstracta, am mai adaugat alte doua metode, findByNameSpecific si findByNamePattern ce functioneaza asemanator: primesc un nume si cauta in baza de date inregistrarile ce contin acest nume: prima clasa returneaza un obiect de tip artist ce contine EXACT acel nume, a doua metoda returneaza o liste de artisti ce contin un nume String-ul oferit.
    </li>
    <li>
      Clasa GenreRepository: Este creata in acelasi mod ca mai sus.
    </li>
    <li>
      Clasa AlbumRepository: Clasa este creata in acelasi mod mentionat deja mai sus. Pe langa acele metode se mai adauga metodele findByReleaseYear si findByArtistId, ce returneaza ambele o lista de albume create in anul specificat/create de artistul specificat.
    </li>
  </ul>
  <li>
    Manager Package
  </li>
  <ul>
    <li>
      Clasa EMFManager: Aceasta clasa se ocupa de crearea unul obiect EntityManagerFactory si de inchiderea acestuia.
    </li>
  </ul>
  <li>
    Clasa InsertFakeData: Contine metoda insertData. Folosind in faker, adaugam in baza de date 500 de artisti noi si 1000 de albume. Inainte de inceperea executiei vom memora timpul de pornire, iar la final memoram in alta variabila timpul in care s-a terminat executia. La final, afisam diferenta end-start.
  </li>
  <li>
    Clasa Test: Contine metoda testJPA in care testam fiecare metoda din clasele ArtistRepository, AlbumRepository si GenreRepository.
  </li>
  <li>
    Clasa Main: Apeleaza metoda Test.testJPA().
  </li>
</ol>
