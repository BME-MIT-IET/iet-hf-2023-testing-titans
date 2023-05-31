# BDD tesztek

## Feladat

A feladat bdd tesztek írása volt, amely a UI-t teszteli viselkedések tesztelése által.
Ehhez a Cucumber nevű eszközt használtam.

## Tesztek

Minden tesztehez két fájl tartozik, egy feature definiciós fájl, mely az [src/test/resources/vvv](../src/test/resources/vvv) mappában található, és egy a tesztet implementáló java osztály, amely az [../src/test/vvv/cucumber](../src/test/vvv/cucumber) mappában található. <br>
A feature fájlban szövegesen van leírva az elvárt működés, és a java osztály ezt a működést valósítja meg és teszteli le.

### Konfiguráció

A feladat egy jelentős részét a cucumber esezköz konfigurálása és működésbe hozása tette ki. Sok konfigurtációs lépést kellett hozzá tennünk, illetve sokat kellett kutatni, hogy ezekre rájöjjünk, így ezzel elég sok időt töltöttünk el.

#### Felmerülő problémák

Miután sikerült helyesen konfigurálnunk a cucumbert egy következő problémába akadtam bele: A unit tesztekkel együtt futtatva a teszteket, nem működtek a cucumber tesztek. Ezt azzal oldottuk meg, hogy a unit testeket egy -Dtest=!RunCucumberTest flaggel, a cucumber teszteket pedig egy -Dtest=RunCucumberTest flaggel futtatjuk. Ezután egy következő probléma is felmerült, ugyanis külön-külön futtatva működtek a cucumber tesztek, azonban amikor egyszerre futtatuk őket, egymásba akadtak, és nem a várt eredményt hozták. Így ezután minden cucumber tesztet külön, egyesével futtattunk. Annak érdekében, hogy mégis le tudjuk futtatni egymás után az összes tesztet készítettünk egy [windows](../cucumber.bat) és egy [bash scriptet](../cucumber.sh), amely egymás után futtatja az összes cucumber tesztet.

### Tesztek írása

A tesztek írása során az alapvető funkciókat teszteltük le a UI/felhasználó oldalról. Igyekeztünk mindent úgy csinálni, mint ha azt ténylegesen csinálhatná egy felhasználó a játékban. Viszont igyekeztünk minél egyszerűbb teszteket létrehozni, hogy ne legyen nagyon sok lépés eljutni a kívánt állapotba.

A tesztelés során továbbá néhány alkalommal szükségünk volt kicsit kiegészíteni, módosítani az eredeti kódot, kisebb hibák, vagy esetleges problémaforrások javításával.

### Tesztek eredménye

Összesen 10 tesztet készítettünk az alapvető funkciók tesztelésére. Minden teszt sikeresen lefut. 
