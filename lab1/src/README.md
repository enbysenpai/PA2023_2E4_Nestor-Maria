Compulsory: *prezentat la laborator* <br>

Homework: Prima data am verificat daca la linia de comanda am dat o valoare pentru n; in caz afirmativ, am parsat string-ul in int. In variabila start am memorat
timpul de inceput al programului, iar variabila booleana tooBig indica daca n este mai mare decat 30_000 sau nu (in caz afirmativ, afisam la final timpul de executie
al programului). Astfel, am creat matricea latin[][] in felul urmator: <br>
Pracurgem matricea, incrementand la fiecare linie noua k-ul (acesta fiind initial 0). Putem asigna fiecarei pozitii o valoare astfel:
<ul>
  <li> daca k+j<=n (daca nu depasim valoarea lui n la adunarea indexului coloanei cu k), atunci latin[i][j]=k+j; </li>
  <li> daca k+j>n (daca deja k este prea mare ca sa poata fi adunat cu j, iar valoarea data sa fie mai mica decat n), atunci latin[i][j]=k+j-n. </li>
</ul>
Acest lucru este posibil deoarece j ia valori cuprinse intre 0 si n-1: daca k este 1 iar j=n-1, atunci k+j<=n este adevarat.<br>

Se va obtine o matrice de forma:<br>
    1 2 ... n <br>
    2 3 ... 1 <br>
    ......... <br>
    n 1 ... n-1 <br>
    
 Acum, va fi necesar sa afisam obiectele de tip String prin care concatenam fiecare element de pe un rand/coloana i, pentru orice i de la 0 la n-1. Vom face asta doar
 daca variabila booleana tooBig este falsa. Pentru a crea String-urile, vom initializa un string cu "" (null) la fiecare linie noua, iar la fiecare coloana acestuia
 i se va alipi valoarea de pe coloana j (  row = row + latin[i][j]; ). Dupa ce iesim din for-ul coloanelor, afisam String-ul. In acelasi mod vom crea String-urile pentru
 coloane, doar ca vom inversa for-urile (ne vom deplasa de pe coloana 0 la coloana n-1). <br>
 
 La final, variabila stop memoreaza timpul final executiei, iar variabila runningTime calculeaza timpul de executie. Daca flagul tooBig este setat pe true, atunci afisam
 timpul de executie.
