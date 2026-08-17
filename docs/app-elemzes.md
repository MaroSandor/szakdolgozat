# Hasonló mobilalkalmazások elemzése

## Elemzés mintája

1. Hogy néz ki a főképernyő?
    - Elinduláskor mit látunk?
    - Elindulás után mi tölt be?
2. Milyen a bejelentés folyamata?
    - Hol található a bejelentés menüpont?
    - Milyen formában jelenik meg?
    - Hogy kell bejelentést tenni? (lépésről lépésre)
    - Kér-e fotót, helyszínt vagy kategóriát?
    - Lehet-e anonim módon bejelentést tenni?
    - Bejelentés után milyen visszajelzést kap a felhasználó?
    - Láthatóak-e mások bejelentései?
3. Egyéb megfigyelés
    - Minden ami pozitív, illetve negatív tényező az alkalmazásban!

---

## Alkalmazások

1. AutóŐrszem (magyar fejlesztés - autósoknak kitalálva)
2. FixMyStreet (brit fejlesztés - témához passzoló)
3. SeeClickFix
4. Traffy Fondue
5. Verbeterdebuurt
6. Járókelő.hu (webalkalmazás)

## AutóŐrszem

- Hogy néz ki a főképernyő?
    - A betöltés közben egy logó jelenik meg fehér háttérrel majd egy effekt után az AutóŐrszem logó (előtérben), egy fotó (háttérben), a képernyő alján pedig a "START" gomb jelenik meg.
    - A "START" gombra nyomva ugyanaz az effekt fut le, ezt követően a Bejelentkezés oldal jelenik meg.** Ezen az oldalon lehetőség van bejelentkezni, jelszót visszaállítani és regisztrálni; általános bejelentkező oldal.
    - Bejelentkezést követően a **főképernyő** fogad mintket.
    - A főképernyőn megtalálható az alkalmazás neve, egy "Risztás küldése" és egy "Riasztásaim" gomb egymás mellett fókusz pontban. Ezek alatt listaszerűen megtalálható egy "Tankolás", "Rendszámaim", "Elküldött Riasztásaim" és "Shop" gomb. A képernyő alján egy navigációs sáv is található, benne a következő menüpontokkal: Főmenü, Beállítás, Profilom, Üzenet.

- Milyen a bejelentés folyamata?
    - A bejelentés folyamatát a "Riasztás küldése" gombra nyomva indíthatjuk el. A gombra nyomva egy új oldalra navigál az alkalmazás ahol 2 attribútumot kell megadnunk. Első a rendszám amit szabadon megadhatunk, ezt a rendszert a beküldés előtt ellenőrzi, létezik-e az adatbázisban. Ha nem akkor visszajelzést ad a felhasználónak. A második attribútum a riasztás oka, amit egy listából tudunk kiválasztani. Ha nincs a megfelelő ok a listában, akkor a lista végén "Egyedi üzenet" opciót kiválasztva saját okot adhatunk meg, ez kötelező.
    - A riasztáshoz nem kér sem fotót, sem helyzetet, csak egy okot és egy rendszámot akinek küldeni fogja az alkalmazás a riasztást.
    - Az alkalmazás nem ad lehetőséget anonim/nyilvános küldés kiválasztására, a riasztást teljesen anonim módon küldi el a címzettnek, feltételezhetően az adatvédelmi okok miatt!
    - A felhasználó, amennyiben a riasztás küldése sikeres volt, tájékoztatást kap erről szöveges formában. Amennyiben a címzett reagál erre, arról is jelzést kap a felhasználó.
    - A riasztások nem publikusak, csak a címzett kap értesítést róla.

- Egyéb megfigyelés:
    - Pozitív:
        - Egyszerű felépítés, könnyű tájékozódás, kellő információ megjelenítés a képernyőn. Nincs tele rakva különféle effekttel.
        - Plusz funkcióként rögzíteni lehet a tankolások idejét és összegét (bár utóbbi korlátozott).
        - Ingyenes használat, támogatási lehetőségek.
    - Negatív:
        - Egy Xiaomi Redmi 5 Plus eszközön kipróbálva elvesztette a reszponzivitását, kisebb az elfoglalt terület, mint a kijelző.
        - Egyszerűsége egyszerre pozitív és negatív; túl egyszerű az alkalmazás, számomra a felépítés nem logikus.
    
    ** Megjegyzés: Az alkalmazás csak regisztrált felhasználóknak elérhető! (egyezés)

## FixMyStreet

- Hogy néz ki a főképernyő?
    - A megnyitás pillanatában az alkalmazás logója látható.
    - A betöltés közben a képernyő felső részében egy rövid, lényegre törő leírás olvasható arról, hogy mit lehet csinálni az alkalmazásban! A képernyő közepén egy logó található az alkalmazás nevével, alatta pedig egy töltőcsík, ami kiírja hogy a betöltés közben milyen ellenőrzéseket, feladatok végez el. Az alkalmazás első használatánál a nyelv kiválasztása kötelező.
    - Kiválasztás után engedélyt kér a helymeghatározásra, ezt követően beméri a helyzetünket. Esetemben (mivel ez egy németek által létrehozott applikáció) nem tudja bemérni a helyzetemet, mert nem vagyok megfelelő helyen; ezt tudatja is velem az alkalmazás!
    - A helymeghatározás után a **főképernyő** egy térkép nézet(!), ahol a térképen kívül 2 gomb lehetőség adott, egy "+" (plusz) jel a képernyő alján középen, valamint a bal felső sarokban egy "Hamburger menü" ikon.
    - A "+" jelet megnyomva kéri, hogy adjunk meg fotót, ezt két módon tehetjük meg, kiválasztás a könyvtárból vagy kép készítése. Ezt követi a bejelentés tovébbi részei, több fotó hozzáadása, hely megadása, probléma típusa (kategória), egy opció amellyel megjelölhető hogy a probléma 20 méteren belül többször is előfordul-e vagy sem, valamint egy opcionális lehetőség megjegyzés hozzáadására. Tovább menve lehet megadni a többi opciót mint az anonimitás és fiókadatok amennyiben nem még nem regisztrált/jelentkezett be a felhasználó. Ezután két lehetőség van, vagy elmenti a felhasználó a bejelentést és később küldi el vagy beküldi a problémát.
    - A "Hamburger menü" ikonra nyomva egy "Settings" menü jön elő ahol olyan menüpontok találhatók meg mint "Profile", "My incidents", "My tracked", "My drafts", az alkalmazás nyelve és a Felhasználási feltételek.
        - "Choose a profile": Lehetőség van bejelentkezni, valamint több fiókot hozzáadni.
        - My incidents, My tracked és My draft menüpontokat helyzetmeghatározás nélkül nem tudtam tesztelni.

- Milyen a bejelentés folyamata?
    - A bejelentést a főképernyőről tudjuk elindítani, a képernyő alján található "+" jellel. A "+" jelre nyomva felugrik egy ablak, ahol megkell adnia a felhasználónak egy fotót; ezt megteheti kiválasztással vagy azonnali fotó készítésével. A fotó megadása után további adatok megadását kéri az alkalmazás, mint további fotók (ha szükséges, de elég 1 is), hely, probléma típusa, egy lehetőség amellyel megjelölhető, hogy a probléma 20 méteren belül többször ismétlődik-e és opcionálisan megjegyzést lehet hozzáadni. Ezután a bejelentést elküldthető.
    - A helyzetmeghatározás hiánya miatt a bejelentést nem tudtam elküldeni.
    - Ennek ellenére próbálkoztam címekkel, és ha egy felhasználó egy olyan esetet próbál bejelenteni, amit már megtettek, a rendszer kiírja hogy már folyamatban van egy ilyen probléma megoldása.

- Egyéb megfigyelés:
    - Pozitív:
        - Letisztult felület, egyértelmű a kezelése. Csak az van rajta aminek lennie kell!
    - Negatív:
        - Helymeghatározás csak és kizárólag Brüsszelen belül érvényes, ha azon kívül tartózkodunk akkor már nem képes meghatározni a helyünket így az alkalmazás lényegi részét már nem tudjuk használni (ha még csak tesztelő jelleggel is).