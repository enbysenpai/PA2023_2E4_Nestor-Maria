COMPULSORY: Prezentat la laborator <br>
HOMEWORK: Din clasa Person creata in compulsory vom crea alte doua clase, Programmer si Designer, inherited din clasa Person. Programatorul va avea un limbaj de programare si anii de experienta asociati, iar designer-ul va avea un Soft de design asociat, cat si stilul preferat de design. La comun vor avea date precum: 
<ul>
<li> id </li>
<li> nume </li>
<li> zi, luna, an nastere </li>
<li> relatia pe care o are cu un angajat/compania </li>
</ul>
Relatia este un map ce contine nodul si un string in care avem memorat tipul relatiei in sine. Metoda compareTo() compara doua pesroane dupa numele lor. Metoda toString() returneaza numele persoanei. Metoda getNodeImportance() returneaza marimea map-ului relationships (numarul de legaturi pe care persoana le are in companie). Metoda getNeighbors() returneaza toate legaturile pe care o persoana le are in companie. <br>

Clasa Designer, cat si clasa Programmer au doar getter-ele si settere-ele asociate, acestea mostenind din clasa Person restul metodelor. <br>

Clasa Company contine date precum:
<ul>
<li> id-ul companiei </li>
<li> numele acesteia </li>
<li> orasul in care se afla </li>
</ul>
Metoda compareTo() compara doua companii dupa nume. Metoda toString() returneaza numele companiei. Metoda getNeighbors() returneaza "vecinii" companiei (legaturile pe care le are cu angajatii sau cu alte companii). getNodeImportance() returneaza numarul de astfel de legauri. <br>

Interfata Node are metodele getName(), getId(), getNeighbors(), getNodeImportance() si metoda getWeight() ce returneaza 0.0. <br>

Clasa Network contine o lista de astfel de noduri, noduri ce retin practic informatii despre angajati, companii si legaturile dintre acestia. Metoda toString() returneaza tot network-ul (detalii despre angajati, detalii despre companii, legaturile care exista si intre cine, etc). Aceasta metoda apeleaza o metoda numita printNetwork() ce creaza un string ce contine toate aceste informatii. In cadrul acestei metode vom sorta nodurile in functie de importanta lor. Importanta unui nod in companie este data de numarul de legaturi pe care o persoana le are. Astfel ca, in clasa Network, vom sorta in mod descrescator persoanele din companie in functie de aceasta importanta. Metoda returneaza un string.
