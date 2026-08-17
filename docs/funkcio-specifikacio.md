# Funkcióspecifikáció

1. Felhasználó
2. Terepes (munkatárs)
3. Irodás (munkatárs)
4. Admin

## Funkcionális követelmények

### Mobil alkalmazás

- Felhasználó:
    - Regisztráció / Bejelentkezés
    - Bejelentés rögzítése (anonim vagy névvel)
    - Saját bejelentés szerkesztése / törlése
    - Bejelentések megtekintése

- Terepes:
    - Bejelentkezés
    - Kiosztott bejelentések megtekintése
    - Kiosztott bejelentés státuszának frissítése
    - Kiosztott bejelentéshez munkavégzés végeztével helyszíni megjegyzés rögzítése fotókkal

### Webalkalmazás - admin felület

- Irodista:
    - Bejelentkezés
    - Bejelentések megtekintése
    - Bejelentés státuszának frissítése
    - Bejelentés kiosztása Terepesnek
    - Bejelentés archiválása / lezárása
    - Új Terepes / Irodista regisztrálása
    - Statisztikák megtekintése

- Admin - rendelkezik az összes Irodista, Terepes és Felhasználó jogosultsággal, kiegészítve az alábbiakkal:
    - Bejelentés szerkesztése / törlése
    - Felhasználók kezelése (szerepkör kezelés, tiltás, feloldás, moderálás)

---

## Nem funkcionális követelmények

### Platform

- Mobil alkalmazás: Android 7.0+ (Nougat)
- Webalkalmazás: modern böngésző (pl. Firefox, Chrome, Safari)

### Biztonság

- Mind a mobilalkalmazás, mind a webalkalmazás HTTPS protokollon keresztül kommunikál a backend-del.
- A felhasználói hitelesítés JWT token alapján történik.
- A jelszavak bcrypt hash alapú titkosítással vannak mentve, jelszó kinyerésére nincs lehetőség!
- Anonim bejelentés esetén személyes adat nem tárolódik az adatbázisban!

### Adatvédelem

- A bejelentések nyilvánosan megtekinthetők, bárki bárkiét!
- A bejelentő neve anonim bejelentés esetén nem látható!
- Nem anonim bejelentés esetén a rendszer jelzi a felhasználónak, hogy a neve nyilvános lesz!

### Elérhetőség

- A mobilalkalmazás az előző indítás alkalmával lekért bejelentéseket offline módban is betölti a legközelebbi használatkor (offline mód), de bejelentés küldése, újak lekérése nem lehetséges, csak online módban.

### Használhatóság

- Az alkalmazás egy mezei felhasználó számára is könnyen kezelhető, egyszerű.
- Bejelentés küldéséhez 3 adat elegendő amiből 1 automatikusan kitöltődik: probléma megnevezése, fotók, hely (engedély esetén helymeghatározás).