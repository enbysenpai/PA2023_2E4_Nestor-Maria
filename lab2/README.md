COMPULSORY: prezentat la laborator <br>
HOMEWORK: am lucrat pe compulsory. Am adaugat in plus:
<ul>
  <li>Am schimbat clasa Location intr-o clasa abstracta
  <li>Clasele City, Airport si GasStation, clasele derivate din Location
  <li>Clasa Problem care retine toate datele problemei si cauta daca exista drum intre doua locatii date
</ul>
Clasa Problem este asemanatoare cu toate celelalte clase: constructori, settere si gettere. Aceasta clasa cuprinde doi constructori, cel default creaza 3 locatii si 3 strazi, iar cel cu parametri creaza problema cu datele oferite in main (acest constructor primeste doua array-uri, locations si roads). Setterele si getterele seteaza, respectiv returneaza informatiile despre locatii si strazi. <br>
Metoda isValid verifica daca datele problemei sunt valide. Daca nu avem niciun obiect de tip Road sau Locations, atunci problema nu este valida. Daca doua strazi sau doua locatii au aceleasi nume, atunci problema nu este valida. Daca, totusi, problema trece aceste teste, problema data este valida.<br>
Metoda travel verifica daca se poate calatori intre doua locatii data. Daca exista un drum intre acestea, se returneaza True. In caz contrar, se returneaza False. <br>
In Main doar verificam daca exista un drum intre doua locatii date. Daca da, atunci afisam acest fapt, altfel afisam ca nu exista un astfel de drum. Mai mult decat atat, in cazul in care problema data nu este valida, nu mai cautam daca exista un drum intre locatii.
