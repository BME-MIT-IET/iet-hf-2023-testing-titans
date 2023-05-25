# Build + CI

## Feladat leírás
1. Build keretrendszer beüzemelése (Maven)
2. CI beüzemelése (Actions)

## Build keretrendszer beüzemelése
Build keretrendszernek a Maven választottuk.
Ez nagyban megkönnyítette a fordítás és tesztelés menetét is.
Megelőző tapasztalat hiányában, eleinte ismerkedtünk és harcoltunk vele.
A végső eredmény a [pom.xml](../pom.xml) fájlban láthatjuk.

### Feladatmegoldás vázlatos menete
1. Fordításhoz és junit tesztekhez kötödő részek beállítása.
A projekt nem követi a Maven által várt formátumot.
Ezért több helyen is finomhangolni kellett a pom fájlt, hogy az elvárt módon működjön.
2. Mocikto keretrendszer függőségek felvétele.
3. SonarCloud működéséhez szükséges property beállítások felvétele.
4. A JaCoCo függőségek felvétele és pluginek beállítása, hogy megfelelő kimenetet adjon.
5. Cucumber keretrendszer függőségek felvétele.
6. A tesztek futtatásakor a JUnit és Cucumber tesztek összeakadtak.
Ezt több órányi munkával sem tudtuk megfejteni, hogy miért.
A megoldás végül az lett, hogy az egységtesztek futtatásához hozzáadtuk a `-Dtest=!RunCucumberTest` kapcsolót.
A BDD tesztek futtatásához pedig a `-Dtest=RunCucumberTest` kapcsolót használtuk.
7. A BDD tesztek is összeakadtak futtatáskor.
Ezeket végül egyesével futtatuk a `"-Dcucumber.filter.tags=@TagName"` kapcsoló segítségével.
Ezeket pedig egy bash szkript segítségével automatikusan futtattuk egymás után.

## CI beüzemelése
Folyamatos integrációhoz a GitHub Actions eszközt választottuk.
Eleinte csak az egységteszteket futtata.
Amennyiben a kód elesett valamelyik teszten, ezt egy vörös X-szel jelölte nekünk az eszköz.
Minimális átírástól eltekintve, ezt automatikusan hoztuk létre a GitHub felületén.
A végső workflow-t a [maven.yml](../.github/workflows/maven.yml) fájlban láthatjuk.

### Feladatmegoldás vázlatos menete
1. Workflow legenárálása GitHubon.
2. Kicsit bonyolódott a helyzet, amikor a SonarCloudot is bekötöttük.
A SonarCloud Quality Gate-jén ugyanis nem mindig mentünk át.
Ehhez ugyanis 80%-os kódlefedettséget kellett volna elérni (ezt mi nem tudtuk átállítani) az új sorokon.
Ezzel megtanultunk együttélni.
3. A feladat tovább bonyolódott, amikor már Cucumber teszteket is készítettünk.
Ennek ugyanis szüksége van valamilyen kijelzővel rendelkező futtató környezetre.
Erre a problémára ismét megoldást jelentett a `-Dtest=!RunCucumberTest` kapcsoló felvétele.
