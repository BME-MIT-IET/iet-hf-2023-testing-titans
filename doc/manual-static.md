# Manual code review + Static analysis

## Feladat:

Statikus analízis végzése SpotBug és SonarLint/SonarCloud alapján, és manuális kód átvizsgálása.

## SpotBug-SonarLint:

A SpotBug/Sonarlint eszközökkel átfésülve a kódot, több típusú hibát is találtunk. Ennek a dependency-je bekerült a pom.xml fájlba 
is. Tipikus hibák közé a következők tartoztak:
- Több sorba volt leírva egysoros kód, pl.: változóba mentettek ki, és azt adtuk vissza, nem egyből az értéket.
  - Javítás: egyből visszaadtuk az értéket, átláthatóság növelése.
- Listáknál *list.size() > 0* használata *IsEmpty()* függvény helyett.
  - Javítás: *IsEmpty()* függvény használata, átláthatóság növelése.
- Random folytonos létrehozása.
  - Javítás: statikus adattagként inicializálás, teljesítmény növelése.
- Betűtípus beégetése.
  - Javítás: Betűtípus kiszervezése külön változóba, karbantarthatóság növelése.
- *@Override* hiányossága
  - Javítás: *@Override* felvétele a felülírt függvények elé, átláthatóság növelése
- Túl nagy láthatóság adása feleslegesen
  - Javítás: Láthatóságok nagyobb korlátozása, egységbezárás növelése
- Nem elmentett és final adattagok nem jelzése
  - Javítás: Transient/Final felvétele az érintett adattagokhoz, átláthatóság növelése
- *Foreach* használatának hiánya
  - Javítás: *Foreach* ciklusok beiktatása a lehető legtöbb helyre, átláthatóság növelése
- Hibás try-catch block
  - Javítás: *try(...)* használata az adott részen.
- Publicus teszt osztály.
  - Javítás: Teszt osztály public paraméterének eltávolítása.
- Tesztelésnél a várt és kapott érték felcserélése az *Assert*-ben.
  - Javítás: Helyes sorrendbe tétel.

## Refaktorálásból eredő hibák - SpotBug/SonarLint

Vannak refaktorálási javaslatok, ahol tudatosan az eszközök szerinti rossz módszert használjuk, mert kijavításuk 
több hibát is maga után von. A következő refaktorálási üzenetekre kiemelt figyelmet kell fordítani:
- *null* visszaadása egy *Collections.emptyList()* helyett.
  - A programban egy helyen máshogy értelmezi ha egy lista üres, vagy null. (class Slot - getGeneticCode())
- *for* használata *foreach* helyett
  - Foreach esetén a collection tartalmát csak változtatni lehet, hozzáadni és törölni belőle elemet nem.
  Néhány helyen több híváson keresztül történik az elemek hozzáadása és elvétele, melyet ezek az eszközök
  nem vesznek észre, így hibát eredményeznek.
   
  
## SonarCloud

A SonarCloud beépítése után új hibákat észlelt a kód, melyeket elkezdtünk review-zni.
- Singleton osztályokra bejelzett
  - Lezártuk az issue-t, tervezői döntés volt.
- Nem használt paraméterek függvényeknél
  - False positive, leszármazott felülírja a függvényt, és használja.
- Egy előző pontban is jelzett *null* visszatérési érték lecserélését jelezte
  - *won't fix* taggel láttuk el, sok idő lenne helyesen kijavítani, tesztek is erre tesztelnek.
- Logger használata System.Println helyett.
  - *SLF4J Logger* használata.