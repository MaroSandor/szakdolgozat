# Szakdolgozat témaválasztás és havi ütemezés

## Téma általános leírása

A szakdolgozat célja egy Android mobilalkalmazás fejlesztése, amely megkönnyíti a közterületek felügyeletét. A rendszer lehetővé teszi a lakosság számára, hogy bejelentéseket tegyenek közterületi problémákról, mint rossz útburkolat, illegális szemétlerakás, leszakadt távvezeték, stb... Az applikáció hasonlóan működik, mint az AutóŐrszem nevű mobilalkalmazás, de közterületi hibák jelentésére optimalizálva. A projekt felgyorsítja a problémák felismerését és kezelést a lakosság bevonásával.

## Elvégzendő munka és ütemezése havi bontásban

- __1-2. hónap - Tervezési fázis és követelmények összegyűjtése__
A projekt megkezdésekor részletes funkcióspecifikációt dolgozok ki, amely tartalmazza a felhasználói és adminisztrátori szerepköröket. Elkészítem az alkalmazás wireframe terveit és adatbázis sémáját. Meghatározom a felhasznált technológiai stacket: Android Studio, Kotlin/Java, REST API backend (Spring Boot), adatbázis (MySQL/PostgreSQL). Ebben a fázisban fogom végezni a hasonló alkalmazások elemzését. A tervezés és elemzés mellett párhuzamosan elkezdem írni a dokumentációt és rögzítem a haladásomat.
- __3-4. hónap - Backend fejlesztés és alapvető frontend__
Elkészítem a REST API-t Spring Boot keretrendszerben, amely kezeli a felhasználói regisztrációt, bejelentkezést és a bejelentések CRUD műveleteit. Implementálom az adatbázis kapcsolatot és az alapvető entitásokat (User, Report, Status). Mobilon elkészítem az alap navigációs struktúrát: loading screen, login/register képernyők, bottom navigation menü és side menu. Integrálom a térképes funkciót Google Maps API-val vagy más egyéb eszközzel. Emellett folytatom ezen részek dokumentálását.
- __5-6. hónap - Fő funkciók implementálása__
Kifejlesztem a bejelentés rögzítési funkciót: képfeltöltés a telefonról, GPS koordináták automatikus rögzítése, kategorizálás és leírás megadása. Létrehozom a bejelentések listázási és szűrési lehetőségeit (aktív/lezárt státuszok). Anonim bejelentés funkció implementálása. A területi munkatársak számára készítek egy egyszerűbb felületet, ahol látják a nekik kijelölt feladatokat és frissíthetik azok státuszát. A dokumentációt kiegészítem ezekkel a funkciókkal és elkezdem a szakdolgozat felépítését a dokumentáció és haladás alapján.
- __7-8. hónap - Admin rendszer és tesztelés__
Web alapú admin felület készítése, ahol az irodisták kezelhetik a beérkezett jelentéseket, módosíthatják státuszukat, archiválhatják őket. Felhasználók és munkatársak adminisztrációja: szerepkörök módosítása, új munkatárs regisztrálása, felhasználói korlátozások beállítása. Átfogó tesztelési fázis kezdődik: unit tesztek írása, integrációs tesztek, beta tesztelés felhasználókkal. Hibák javítása, teljesítmény optimalizálás. Rögzítem a tesztelés eredményeit és befejezem dokumentációt, valamint a szakdolgozat nyers változatát.
- __9-10. hónap - Dokumentáció és véglegesítés__
Az utolsó hónapban finomítom a szakdolgozat írott részét, amely tartalmazza a probléma bemutatását, a tervezési döntéseket, az implementáció részleteit és az eredmények értékelését. Felhasználói dokumentáció készítése képernyőképekkel. Kód refaktorálás és kommentelés. Az alkalmazás végső verziójának elkészítése és bemutató előkészítése.
