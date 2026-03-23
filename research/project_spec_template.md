# PROJECT SPEC — [Projekt neve]

> Ez egy template fiktív projekttel illusztrálva.
> Másold át a PROJECT_SPEC.md fájlba és töltsd ki a saját projektedre.

---

## 1. Projekt áttekintés

**Projekt neve:** ParkAlert – Parkolási szabálysértés bejelentő app
**Verzió:** 1.0.0
**Státusz:** Tervezési fázis
**Utoljára frissítve:** 2026-03-23

### Rövid leírás
A ParkAlert egy mobilalkalmazás, amellyel a lakosok egyszerűen bejelenthetik a szabálytalan parkolási eseteket fotó, helyszín és leírás megadásával. A bejelentések egy admin felületen kezelhetők, ahol az illetékes hatóság nyomon követheti és kezelheti az eseteket.

### Probléma, amit megold
Jelenleg a szabálytalan parkolás bejelentése telefonos ügyfélszolgálaton keresztül zajlik, ami lassú, nehézkes és nem nyomon követhető. Nincs visszajelzés a bejelentőnek, és az adatok nem kerülnek digitálisan rögzítésre.

---

## 2. Célközönség

| Szerepkör | Leírás |
|-----------|--------|
| Lakos (User) | Bejelentést tesz, nyomon követi saját bejelentéseit |
| Admin | Bejelentéseket kezeli, státuszt módosít, statisztikákat lát |

---

## 3. MVP — Minimum Viable Product

Az MVP tartalmazza:
- Regisztráció / bejelentkezés
- Bejelentés létrehozása (fotó + helyszín + leírás)
- Saját bejelentések listázása
- Admin: bejelentések listázása és státusz kezelése

Az MVP **nem** tartalmazza:
- Push értesítések
- Térkép nézet
- iOS verzió
- Statisztikai dashboard

---

## 4. Funkcionális követelmények

### 4.1 Felhasználó (lakos)

- [ ] **REG-01** Regisztráció e-mail + jelszóval
- [ ] **REG-02** Bejelentkezés
- [ ] **REP-01** Új bejelentés létrehozása (fotó, GPS koordináta, szöveges leírás, kategória)
- [ ] **REP-02** Saját bejelentések listázása
- [ ] **REP-03** Bejelentés részleteinek megtekintése
- [ ] **PRO-01** Profil adatok megtekintése

### 4.2 Admin

- [ ] **ADM-01** Összes bejelentés listázása, szűrése
- [ ] **ADM-02** Bejelentés státuszának módosítása (Új → Folyamatban → Lezárt)
- [ ] **ADM-03** Felhasználók listázása
- [ ] **ADM-04** Felhasználó tiltása

---

## 5. Nem funkcionális követelmények

| Kategória | Követelmény |
|-----------|-------------|
| Biztonság | JWT alapú autentikáció, HTTPS kötelező |
| Teljesítmény | Listaoldal max. 2 másodperc betöltési idő |
| Kompatibilitás | Android 7.0+ (API 24+) |
| Skálázhatóság | MVP szinten nem kritikus |
| Offline mód | Nem támogatott az MVP-ben |

---

## 6. Képernyők (Android)

1. Splash / Loading screen
2. Login screen
3. Registration screen
4. Home / News feed
5. Reports list
6. Report detail
7. New report (kamera + térkép)
8. Profile

---

## 7. Technológiai stack

| Réteg | Technológia | Indoklás |
|-------|-------------|----------|
| Android | Kotlin + Jetpack Compose | Modern, Google által ajánlott |
| Backend | Spring Boot 3 | Jól dokumentált, Java/Kotlin ökoszisztéma |
| Adatbázis | PostgreSQL | Megbízható, relációs, ingyenes |
| Autentikáció | JWT (RS256) | Stateless, biztonságos |
| Webadmin | React + shadcn/ui | Gyors fejlesztés, modern komponensek |
| Hosting | Render (free tier) | Ingyenes demo célra |

---

## 8. Határidők / Mérföldkövek

| Fázis | Tartalom | Határidő |
|-------|----------|----------|
| Tervezés | Spec, wireframe, DB séma | 2026-04-01 |
| Backend MVP | Auth + Report API | 2026-04-20 |
| Android MVP | Auth + Report képernyők | 2026-05-10 |
| Webadmin MVP | Dashboard + Bejelentések | 2026-05-25 |
| Tesztelés | Unit + manuális tesztek | 2026-06-05 |
| Szakdolgozat leadás | Végleges dokumentum | 2026-06-15 |
