#Unit tests

##Feladat

A feladat unit tesztek írása volt, amelyek a kód egységeit tesztelik elvárt működés szempontjából.

##Tesztek írása

A tesztek az [src/test/vvv](../src/test/vvv) mappában találhatóak. <br>
Tesztelésre a Game és Player osztályt választottuk, mivel ez a két legnagyobb osztály, amelyek a sok felelősséggel bírnak
a játék során. Továbbá teszteltük az Agent, AxeEquipment, ChoreaAgentEffect, Inventory, MapLoader osztályokat, mivel ezek
gyakran vannak használva a játékban

###Tesztek írásának menete
1. Tesztelendő osztály meghatározása
2. Új teszt osztály létrehozása
3. A tesztelendő osztály példányosítása minden teszt előtt
4. A tesztelendő osztály esetleges beállítása létrehozás után, hogy determinisztikusak legyenek a tesztek
5. Tesztek vázának létrehozása az osztály függvényeinek
6. Minden függvénynél meghatározni a lefutások lehetséges számát
7. Minden lefutáshoz új teszteset felvétele
8. Minden lefutás elvárt eredményének meghatározása
9. Teszt megírása, hogy ellenőrizze az elvért eredményeket adják-e a lefutások
10. Tesztek lefuttatása
11. Esetleges hibák felvétele hibajegyzékbe és későbbi javítása

###Tesztelés során talált hibák javítása
Tesztelés során kisebb hibákra bukkantunk a program lefutásában. Voltak gyorsan javítható problémák is, mint amikor 
például a függvény neve és visszatérési értéke nem volt összhangban (pl.: csinald() függvény akkor tért vissza igazzal, 
ha nem sikerült végrehajtani a cselekvést).<br>
asd

###Tesztek írásának eredménye
A tesztekkel 40%-os kódlefedettséget értünk el. <br>
Sokat tanultunk a mockito-ról, illetve a reflection-ről. Segített, hogy ha egy osztálynak nem kell semmilyen tulajdonsága 
sem egy teszt során és nincs szükség az osztály függvényeire, akkor mock-olással létre lehet hozni egy alternatív példányt,
ami nem okoz mellékhatást egy olyan tesztesetben, ahol ennek az osztálynak a működése lényegtelen.