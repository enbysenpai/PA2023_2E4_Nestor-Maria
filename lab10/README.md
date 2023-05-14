COMPULSORY: <em>prezentat la laborator</em><br>
OPTIONAL: Tema este alactuita din doua proiecte separate (kind of):
<ol>
  <li>Client side:
  Acest "proiect" are doua clase:
  <ol>
    <li>
      GameClient: Conecteaza un client la server utilizand un socket. Acesta trimite comenzi folosind un PrintWriter si citeste comenzile primite de la server folosind un BufferedReader. Metoda principala este play(). Aici se creaza un socket catre o adresa IP si PORT declarate. Clientul trimite comenzi catre server, acesta le prelucreaza, iar clientul le citeste si le afiseaza pe ecran.
    </li>
    <li>
      Main: Creaza un obiect de tip GameClient si apeleaza metoda play().
    </li>
  </ol>
    </li>
  <li> 
    Server side:
  
  <ol>
    <li>
      game Package:
      <ul>
        <li>
           Clasa Player: Are grija de conexiunea unui singur jucator. Cu ajutorul unui socket acesta citeste mesajele primite de la jucator, serverul le proceseaza, si trimite mesajele primite catre jucator. Metoda init() initializeaza conexiunea catre joc. Metoda processCommand() proceseaza comanda primita. Daca este comanda "move nr nr", atunci se apeleaza functia processMoveCommand(). Aceasta verifica daca positia ceruta de catre client este una valida. De asemenea, verifica daca nu cumva jucatorul curent a castigat jocul sau daca este remiza (fiecare jucator va primi un mesaj corespunzator). 
        </li>
        <li>
          Clasa Board: Este o reprezentarea a tablei de joc. Metoda resetBoard() reseteaza tabla, reinitializand-o cu 0. Metoda addPiece() primeste trei parametri si verifica daca pozitia data prin parametri x si y este valida; in caz afirmativ, in pozitia respectiva adauga id-ul jucatorului.
        </li>
        <li>
          Clasa Game: Reprezinta logica din spatele jocului. Jocul se desfasoara cu 2 jucatori, scopul fiind ca unul dintre acestia sa formeze o linie de 5 piese de aceeasi culoare. Metoda addPiece() verifica prezenta celor doi jucatori si apeleaza metoda din Board.class, addPiece(). Metoda checkWin() verifica cele 4 cazuri posibile:
          <ul>
            <li>
              S-a format o linie pe orizontala: metoda checkHorizontalLine()
            </li>
            <li>
              S-a format o linie pe verticata: metoda checkVerticalLine()
            </li>
            <li>
              S-a format o diagonala: (checkDiagonal())
              <ul>
                <li>
                  spre dreapta: checkRightDiagonal()
                </li>
                <li>
                  spre stanga: checkLeftDiagonal()
                </li>
              </ul>
            </li>
          </ul>
          De asemenea, in clasa Game avem si metoda checkDraw() care verifica daca nu cumva s-a ajuns la remiza (toate casutele sunt ocupate dar nicio linie formata.
        </li>
      </ul>
    </li>
    <li>
      Clasa ClientThread: Asigura comunicarea dintre server si client. Exista o lista de tip Game ce retine jocurile aflate in desfasurare. In cazul in care un astfel de joc nu mai are jucatori, acesta este sters din lista. In momentul conexiunii unui jucator, acesta se poate afla in doua situatii:
      <ul>
        <li>
          Intra intr-un joc nou creat si asteapta un partener
        </li>
        <li>
          Intra intr-un joc in care deja se gaseste un jucator
        </li>
      </ul>
    </li>
    <li>
      Clasa GameServer: Aici se "asculta" eventualele noi conexiuni care se vor a fi create cu serverul. Cand se creaza o noua conexiune cu un client, se creaza o noua instanta de ClientThread.
    </li>
    <li>
      Clasa Main: Creaza un obiect de tip GameServer
    </li>
  </ol>
 </li>
</ol>
