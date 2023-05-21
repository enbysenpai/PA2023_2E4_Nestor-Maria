COMPULSORY: Intr-un proiect Spring Boot am creat 4 mari package-uri:
<ol>
  <li>
    Entity: Aici avem doua clase, Game si Player, in care mapam in baza de date tabelele cu acelasi nume.
  </li>
  <li>
    Controller: Aici sunt doua clase, GameController si PlayerController, in care avem implementarea controller-elor folosite in proiect. In cazul nostru, pentru laborator, avem metoda getAllPlayers ce, pe baza unui HTTP GET request, ofera lista cu toti jucatorii inregistrati in baza de date. 
  </li>
  <li>
    Service: In cele doua clase corespondente controllerelor, se vor implementa metodele cerute de controllere.
  </li>
  <li>
    Repository: In acest package avem doua interfete ce extind JpaRepository, ce ajuta in implementarea serviciilor.
  </li>
</ol>
<br><br>
HOMEWORK: In plus fata de laborator: 
<ul>
  <li>
    Via unui HTTP POST request, am creat controllerul si serviciul ce adauga un nou jucator in baza de date.
  </li>
  <li>
    Via unui HTTP PUT request, am creat controllerul si serviciul ce modifica un player existent din baza de date.
  </li>
  <li>
    Via unui HTTP DELETE request, am creat controllerul si serviciul ce sterge un jucator din baza de date.
  </li>
  <li>
    Via unui HTTP GET request, am creat controllerul si serviciul ce ofera lista de jocuri existente in baza de date.
  </li>
  <li>
    In main, am "creat" clientul ce invoca fiecare metoda mentionata si verifica daca aceasta functioneaza cum ar trebui (primim status code-ul 200OK si raspunsul asteptat).
  </li>
</ul>
