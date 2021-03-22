# Tema2POO-SmartTermostat
[Tema2 Programare Orientata pe Obiecte (2019-2020, seria CB)]


Tema presupune implementarea unui sistem pentru optimizarea incalzirii unei locuinte.
Analizand senzorii de temperatura (si optional umiditate) din fiecare camera a 
locuintei, sistemul centralei stabilizeaza, daca este necesar, temperatura si porneste 
termostatul.

Sunt implementate clasele TempState(temperatura) si HumState(umiditate) folosite 
pentru a asocia unei temperaturi(umiditati) un timestamp exprimat in secunde prin
care se afla intervalul de care apartine temperatura(umiditatea).
Aceste clase implementeaza interfata Comparable si suprascriu metoda compareTo de care
este nevoie atunci cand va fi nevoie sa sortam temperaturile.

Clasa Device simuleaza un dispozitiv care retine datele primite de la senzori. Fiecare
camera are un Device. Clasa contine campurile tempInterval si humInterval, care sunt
ArrayList de ArrayList (reprezentarea unei matrici pe care indicii de linie sunt
intervalele in care se afla datele si liniile contin temperaturile/umiditatea asociate 
cu timestamp-ul). Indicele de linie este calculat ca referinta de la timestamp-ul global.
Metodele addTemperature si addHumidity adauga in structura o noua temperatura/umiditate
in linia corecta, ArrayList-ul ramanand sortat la sfarsitul introducerii de date.
Metodele lastTemp() si lastHumidity() returneaza temperatura minima din ultima ora in
care s-au inregistrat date.

Clasa Room descrie o camera. Fiecare camera are un Device in care sunt retinute informa-
tiile. Metodele lastTempRoom() si lastHumRoom() returneaza temperatura/umiditatea minima
din ultima ora din camera respectiva, iar tempPerArea() si humPerArea() calculeaza 
produsul dintre temperatura medie si suprafata. Aceste valori vor fi folosite la calculul
mediei ponderate a temperaturilor.
Metoda listTempInInterval listeaza temperaturile din fiecare camera care se incadreaza 
intr-un interval de timp dat ca parametru.

Clasa House simuleaza locuinta. Aceasta are un ArrayList rooms in care sunt salvate 
camerele si informatiile asociate. Metoda addRoom adauga o camera. findRoomIndex si
findInterval calculeaza indexul camerei in rooms dupa nume si respectiv calculeaza
intervalul in care se afla o temperatura.
Metodele observeRoom si observeHRoom adauga informatiile transmise de senzori despre 
camere. trigetHeat arata daca este nevoie de pornirea centralei sau nu. Se calculeaza
o medie ponderata a temperaturilor in functie de suprafata si daca este mai mica decat
temperatura globala dorita se porneste centrala. Daca se inregistreaza umiditati se
calculeaza in mod asemanator si media umiditatilor. Daca aceasta este mai mare decat 
umiditatea dorita nu se va porni centrala indiferent de media temperaturilor.
Listarea temperaturilor din camere la comanda LIST se face prin listRoomTemp.
Schimbarea temperaturii globale se realizeaza cu changeTemp.

Clasa Main ruleaza programul. Se citesc din fisierul de intrare datele necesare.
Se obtin din prima linie numarul de camere, temperatura globala, timestamp-ul si,
optional, umiditatea globala. Se creeaza un obiect de tip House. Se adauga camerele
si se citesc comenzile. In functie de comanda data (OBSERVE, OBSERVEH, TRIGGER HEAT,
TEMPERATURE, LIST) se efectueaza operatiile cerute.


