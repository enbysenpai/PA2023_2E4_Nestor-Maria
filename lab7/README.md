COMPULSORY: prezentat la laborator<br>
HOMEWORK: Tema este structurata in 7 clase:
<ol>
<li>
MAIN: In main vom declara timekeeper-ul, il setam ca daemon si pornim thread-ul acestuia. Urmeaza apoi sa declaram numarul de roboti(agenti), sa cream thread-urile acestora si sa le pornim de asemenea. Comenzile date de la tastatura vor fi citite tot in main, folosind un Scanner: intr-un while(true), procesam comenzile primite:
<ul>
<li>
stop: opreste thread-urile robotilor
</li>
<li>
pause: daca se transmite doar comanda pause, atunci toti robotii se opresc. Daca se transmite <em>pause numeRobot</em>, atunci se va opri doar acel robot. Daca se adauga si un numar de secunde, atunci robotul se va opri pentru acel numar de secunde si va porni fara a fi necesara pornirea sa manuala.
</li>
<li>
start: daca se transmite doar comanda start, atunci toti robotii vor porni. Daca se transmite comanda <em>start numeRobot</em> atunci doar acel robot va porni.
</li>
<li>
orice alt cuvant: se va afisa pe ecran "Unknown command" si nu se va intampla nimic cu procesele active.
</li>
</ul>
</li>

<li>
TOKEN: Fiecare obiect de tip token reprezinta un numar intreg ce va putea fi adaugat.
</li>

<li>
CELL: Clasa cell contine o lista de tokens si un flag visited. Daca celula este vizitata, flag-ul va fi schimbat in true si se vor adauga un numar n de tokens.
</li>

<li>
SHARED MEMORY: memoria comuna a robotilor; contine o lista neordonata de n^3 tokens, cu numere intregi intre 0 si n^3-1. Metoda extractTokens() este sincronizata si va extrage pentru fiecare robot un numar de n tokens.
</li>

<li>
EXPLORATION MAP: contine harta vizitata de roboti. Harta este reprezentata de o matrice de n*n, fiecare celula reprezentand un obiect de tip Cell. Metoda visit() este sincronizata si este de tip boolean. Daca pozitia (row,col) nu a fost vizitata, atunci robotul va putea extrage un numar n de tokens din memoria comuna si ii va putea adauga in cell - reuturn true. Daca este vizitata deja, se afiseaza pe ecran un mesaj de informare ("failed to access the cell") iar metoda returneaza false.
</li>

<li>
ROBOT: clasa ce descrie comportamentul unui singur thread robot. Initial, fiecare robot este plasat intr-o celula aleatoare, ce este vizitata. In lista de integers visitedCells memoram celulele deja vizitate dupa formula: row*mapSize+col (pentru o matrice de 5*5, lista va putea contine numere intre 0 si 24, reprezentand o celula anume din matrice). In aceasta clasa sunt doua flag-uri, running si paused. Cat timp running este true, thread-ul inca mai poate vizita celule. Cand acest flag este false, thread-ul nu va mai putea fi repornit. In schimb, daca flag-ul paused este true, thread-ul este oprit, insa el poate fi repornit prin comanda start. Robotul isi poate alege urmatoarea celula de vizitat astfel:
<ul>
Intr-un while(!done)
<li>
Daca deasupra sa celula nu a fost vizitata, atunci va vizita acea celula. Adaugam celula in lista visitedCells. done=true.
</li>
<li>
Altfel, daca in dreapta sa celula nu a fost vizitata, o va vizita pe aceasta. Adaugam celula in lista visitedCells. done=true.
</li>
<li>
Altfel, daca sub el celula nu a fost vizitata, atunci robotul o va vizita pe aceasta. Adaugam celula in lista visitedCells. done=true.
</li>
<li>
Altfel, daca in stanga sa celula nu a fost vizitata, o va vizita pe aceasta. Adaugam celula in lista visitedCells. done=true.
</li>
<li>
Altfel, inseamna ca toti vecinii sai au fost deja vizitati de cineva, asa ca eliminam ultima celula adaugata in lista visitedCells (fiind cea in care se gaseste la moment robotul) si mutam robotul inapoi pe celula precedenta, folosind formulele:
<ul>
<li>
this.row = visitedCells.get(visitedCells.size() - 1) / 5;
</li>
<li>
this.col = visitedCells.get(visitedCells.size() - 1) % 5;
</li>
</ul>
De exemplu, daca visitedCells.get(visitedCells.size() - 1)=13, asta inseamna row=2, col=3. Deci, pe linia 2, coloana 3 (cu i,j de la 0 la n-1). <br>

![image](https://user-images.githubusercontent.com/100276356/233071122-859f4d77-608d-4c46-8d26-ef1584bf52d3.png)

Nu vom schimba valoarea lui done (done=false in continuare). Asta asigura faptul ca robotul va continua gasirea unui vecin liber. Daca insa lista visitedCells nu mai are elemente, robotul isi termina parcurgerea si se opreste.
</li>
</ul>
Pentru fiecare celula vizitata, avem un in noOfCells ce va fi incrementat. Dupa ce un robot isi va termina executia, noOfCells va fi inmultit cu numarul de tokens adaugat intr-o celula (mapSize tokens) si va fi afisat pe ecran numarul total de tokens plasati de robot.
</li>

<li>
TIMEKEEPER: acest timekeeper functioneaza in milisecunde. Cand va fi pornit, vom initializa o valoarea startTime ce va contine timpul de pornire al procesului. Intr-un while(true), la fiecare pas vom (re)initializa o valoare, elapsedTimeMillis, ce retine diferenta dintre timpul curent si timpul de incepere al executiei. La afisare, fiind masurat in milisecunde, vom transforma numarul in secunde (elapsedTimeMillis/1000). In Main vom mentiona cate secunde vrem ca acest cronometru sa functioneze. Astfel ca, daca elapsedTimeMillis>timeLimitMillis, atunci afisam mesajul "Time exceeded" si oprim fortat procesul (System.exit(0)).
</li>
</ol>
