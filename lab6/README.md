COMPULSORY: prezentat la laborator <br>
HOMEWORK: am schimbat urmatoarele clase prezentate in compulsory:
<ul>
  <li>
      ControlPanel: 
      <ul>
         <li>
           Actiunea butonului loadBtn a fost creata cu lambda (altfel, nu mai functiona)
         </li>
        <li>
           Am adaugat butonul de Undo a carui functionalitate este descrisa in clasa DrawingPanel
         </li>
       </ul>
  </li>
  
  <li>
      ConfigPanel:
      <ul>
          <li>
              Am adaugta functionalitatea butonului createGame ce creaza un joc in functie de numarul de noduri date.
          </li>
          <li>
              Butonul saveState salveaza starea actuala a jocului. Acest buton se foloseste de o clasa nou adaugata, GameState. 
          </li>
          <li>
              Butonul loadState incarca starea ultimului joc salvat. Acest buton se foloseste clasa GameState cu ajutorul careia creaza un canvas in clasa DrawingPanel.
          </li>
      </ul>
  </li>
  <li>
      DrawingPanel:
      <ul>
        <li>
          Am adaugat o lista de LineSegments ce retine obiecte de tipul LineSegments, o clasa nou adaugata, cat si o lista de LineSegments ce retine liniile deja colorate.
        </li>
        <li>
           Am adaugat un mouseListener si am implementat doua din clasele acestuia:
           <ul>
            <li>
              mousePressed: prima data verificam daca a fost apasat un nod la o distanta de cel mult 10 pixeli. Daca da, asteptam click pe un alt nod astfel incat segmentul delimitat de acestea sa fie colorat. Altfel, daca am apasat pe o linie la distanta de cel mult 10 pixeli, atunci coloram linia direct.
            </li>
            <li>
              mouseReleased: cand lansam click-ul, verificam daca metoda checkTriangles() este adevarata. Daca nu, la linia de comanda afisam "Not yet", insemnand ca inca nu s-a format un triungi cu aceeasi culoare. Daca da, apelam metoda createWinnerScreen().
            </li>
           </ul>
        </li>
        <li>
            metoda createWinnerCreen(): afiseaza pe canvas textul "Game over"
        </li>
        <li>
            metoda distanceToPoint(): calculeaza distanta pana la un punct folosind formula distantei euclidiene dintre doua puncte
        </li>
        <li>
            metoda colorSegment(): coloreaza linia cu o culoare calculata
        </li>
        <li>
            metoda distanceToLine(): calculeaza distanta pana la o linie folosind formula distantei euclidiene; (x3,y3) semnifica proiectia pe aceasta linie
        </li>
        <li>
          metoda createDeserialized(): creaza board-ul dupa deserializare
        </li>
        <li>
          drawDeserializedLines(): pentru ca noi memoram liniile colorate, folosim aceasta metoda in locul metodei drawLines() pentru a creea jocul salvat
        </li>
        <li>
          checkTriangle(): verifica daca s-a creat un triunghi cu aceleasi culoare
        </li>
        <li>
          checkCoordinates(): verifica coordonatele liniilor; utilizata in checkTriangle()
        </li>
        <li>
          undoGame(): functionalitatea butonul Undo din ControlPanel
        </li>
      </ul>
  </li>
  <li>
      GameState: retine date precum obiectele de tip LineSegment, numarul de noduri, urmatorul index de culoare. Este folosita pentru serializare/deserializare (<em>implements Serializable</em>).
  </li>
  <li>
      LineSegment: retine coordonatele limitelor segmentului, cat si culoarea acestuia.
  </li>
</ul>

Restul claselor raman aceleasi.
