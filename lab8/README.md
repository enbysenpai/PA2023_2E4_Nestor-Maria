COMPULSORY: prezentat la laborator <br>
OPTIONAL: (Din Compulsory exista cateva inregistrari deja introduse)<br>
Clasele:
<ol>
<li>
DB: Folosind Apache Commons DBCP, clasa ofera un connection pool catre o baza de date MySQL. Clasa getConnection() returneaza o conexiune din acel pool. Obiectul de tip BasicDataSource este utilizat pentru a crea si gestiona un connection pool.
</li>
<li>
DataReader: La o locatie data (memorata in variabila path) exista fisierul .csv (albumlist.csv) ce contine informatiile din link-ul <a href="https://www.kaggle.com/datasets/notgibs/500-greatest-albums-of-all-time-rolling-stone">Rolling Stone's 500 Greatest Albums of All Time</a>. Clasa DataReader contine doua metode:
<ul>
<li>
readFile(): creaza un obiect de tip CSVReader ce foloseste un obiect de tip FileReader(path). 
</li>
<li>
readFromFile(): citeste fiecare linie din fisierul .csv. Initial vom citi doua linii deoarece prima reprezinta header-ul. Cat timp inca exista linii in fisier:
<ul>
<li>
Extragem din fisier valorile pentru: releaseYear, albumTitle, artistName, genres, subgenres (ultimele doua fiind adaugate in tabelul genres).
</li>
<li>
Cream un obiect de tip Artist pentru care ii setam numele. Daca acesta exista deja in baza de date, vom cauta acest artist in baza de date. Altfel, va fi adaugat. Vom extrage la final id-ul artistului.
</li>
<li>
Cream un obiect de tip Album pentru care ii vom seta titlul, anul lansarii, id-ul artistului (extras mai devreme). Daca acest album exista deja in baza de date, il vom cauta in aceasta. Altfel, il adaugam. Extragem id-ul albumului.
</li>
<li>
Intr-un String[] genres extragem, din coloana "Genres" a fisierului .csv, doar genurile muzicale (celula contine cuvinte despartite prin virgula; facem split(", ") peste aceasta). Cu un for parcurgem array-ul. Unele cuvinte contin si "& " la inceputul lor, fapt pentru care verificam acest lucru. Daca un cuvant se regaseste in acest caz, extragem subsirul ce incepe de pe pozita 2. In aceeasi maniera ca mai sus, cream un obiect de tip Genre caruia ii setam numele. Daca exista in baza de date il cautam, daca nu il adaugam. Extragem id-ul genului si adaugam in tabela album_genres relatia dintre genre si album (daca nu exista deja).
</li>
<li>
Intr-un String[] subgenre extragem, din coloana "Subgenres" a fisierului .csv, genurile muzicale. Acestea vor fi tratate tot ca genres, deci le vom adauga ca mai sus.
</li>
<li>
La final, trecem la urmatoarea linie din fisier.
</li>
</ul>
</li>
</ul>
</li>
<li>
Album, Artist, Genre: Tot ce s-a schimbat de la Compulsory este faptul ca am folosit dependenta lombok.
</li>
<li>
GenresFaculty, ArtistFaculty, AlbumFaculty: Diferit de Compulsory este faptul ca metoda create() este de tip boolean - daca un obiect nu exista deja in baza de date, va fi adaugat si va returna true. Altfel, va returna false si nu va fi adaugat. Acest lucru ne ajuta in clasa DataReader pentru a vedea in ce stadiu se gasesc obiectele si pentru adaugarea lor eficienta in baza de date. De asemenea, am renuntat in a returna liste acolo unde nu era necesar si am creat metode de afisare a tuturor inregistrarilor din tabelele corespondente (prin metoda showAll()).
</li>
<li>
ArtistAlbumFaculty: Nu exista o tabela corespondenta in baza de date. Aici se gasesc doua metode:
<ul>
<li>
getAlbums(): Dat fiind id-ul unui artist, metoda returneaza o lista cu albumele acestuia. 
</li>
<li>
getArtist(): Dat fiind id-ul unui album, returneaza interpretul acestuia. 
</li>
</ul>
</li>
<li>
ArtistGenreFaculty: Nu exista o tabela corespondenta in baza de date. Aceasta clasa contine patru metode:
<ul>
<li>
getArtistsGenresById(): Se da un id de artist. Metoda returneaza toate genurile muzicale distincte pe care acesta le interpreteaza.
</li>
<li>
getArtistGenresById(): Se da un nume de artist. Metoda returneaza toate genurile muzicale distincte pe care acesta le interpreteaza.
</li>
<li>
getGenresArtistsById(): Se da id-ul unui gen muzical. Metoda returneaza toti artistii ce interpreteaza acest gen.
</li>
<li>
getGenresArtistsByName(): Se da numele unui gen muzical. Metoda returneaza toti artistii ce interpreteaza acest gen.
</li>
</ul>
</li>
<li>
Main: Aici se creaza conexiunea la baza de date, se face conexiunea la fisier si se invoca metoda readFromFile() din clasa DataReader. La final, inchidem conexiunea.
</li>
</ol>
BONUS: Am creat doua tabele noi in baza de date, "playlists" si "playlist_albums", fara inregistrari. In Java am creat un model pentru obiecte de tip Playlist. In PlaylistFaculty putem crea un nou playlist (un obiect Playlist avand atributele id, name si creationTimestamp), putem cauta un playlist dupa oricare dintre atributele sale si putem afisa toate inregistrarile din tabelul "playlists". In clasa PlaylistAlbumsFaculty vom crea legaturile dintre un playlist si un album prin id-urile corespondente acestora. Astfel ca, in aceasta clasa, putem crea o astfel de relatie, putem cauta toate albumele dintr-un playlist pe baza id-ului playlist-ului respectiv sau putem cauta toate playlist-urile in care un album se gaseste (pe baza albumId-ului).<br>
In clasa PlaylistFaculty am creat logica unui playlist maximal:
<ul>
<li>
Preluam, intr-o lista de albume, toate albumele existente in baza de date.
</li>
<li>
Sortam albumele dupa data lansarii (eu am ales in ordine descrescatoare; nu conteaza ordinea).
</li>
<li>
In cele trei HashSet-uri vom avea grija de anii de lansare, id-ul artistilor si id-ul genurilor muzicale deja folosite in crearea listei de albume maximalPlaylists. 
</li>
<li>
Iteram peste toate albumele din baza de date si verificam:
<ul>
<li>
Daca se gaseste anul lansarii albumului curent in HashSet-ul corespunzator, trecem la urmatorul album.
</li>
<li>
Daca se gaseste id-ul artistului ce a creat albumul curent in HashSet-ul corespunzator, trecem la urmatorul album.
</li>
<li>
Daca se gaseste vreun gen muzical deja existent in albumul curent in HashSet-ul corespunzator, trecem la urmatorul album.
</li>
</ul>
<li>
Daca algoritmul trece de pasii anteriori, albumul este adaugat listei maximalPlaylists, iar cele trei HashSet-uri sunt actualizate cu valorile corespunzatoare albumului nou adaugat (releaseYear, artistId, si toate genreId-urile).
</li>
</li>
<li>
Dupa ce terminam iteratia, cream un playlist nou, avand numele "Maximal Playlist", apoi adaugam toate legaturile dintre playlist-ul nou creat si albumele adaugate.
</li>
</ul>
