# DATA SCHEMA — [Projekt neve]

> Ez egy template fiktív projekttel illusztrálva.
> Másold át a saját data_schema.md fájlba és töltsd ki a projektedre.

---

## Miért kell ez a dokumentum?

Az adatbázis séma a backend és a frontend közös "szerződése" — meghatározza, hogy milyen adatok léteznek, hogyan kapcsolódnak egymáshoz, és milyen megkötések vonatkoznak rájuk. Ha ez nincs dokumentálva:
- A backend és az Android app más struktúrában gondolkodik
- Nehéz belépni egy másik fejlesztőnek (vagy 3 hónap után önmagadnak)
- A szakdolgozatban az adatmodell fejezet alapja ez lesz

---

## 1. Entitások

### `users`

| Oszlop | Típus | Megkötés | Leírás |
|--------|-------|----------|--------|
| `id` | UUID | PK, NOT NULL | Egyedi azonosító |
| `email` | VARCHAR(255) | UNIQUE, NOT NULL | Bejelentkezési email |
| `password_hash` | VARCHAR(255) | NOT NULL | BCrypt hash |
| `full_name` | VARCHAR(100) | NOT NULL | Teljes név |
| `role` | ENUM('USER','ADMIN') | NOT NULL, DEFAULT 'USER' | Jogosultság |
| `is_active` | BOOLEAN | NOT NULL, DEFAULT true | Tiltott-e |
| `created_at` | TIMESTAMP | NOT NULL, DEFAULT NOW() | Regisztráció ideje |

---

### `reports`

| Oszlop | Típus | Megkötés | Leírás |
|--------|-------|----------|--------|
| `id` | UUID | PK, NOT NULL | Egyedi azonosító |
| `user_id` | UUID | FK → users.id, NOT NULL | Bejelentő |
| `category_id` | INT | FK → categories.id | Kategória |
| `title` | VARCHAR(200) | NOT NULL | Rövid cím |
| `description` | TEXT | | Részletes leírás |
| `status` | ENUM('NEW','IN_PROGRESS','CLOSED') | NOT NULL, DEFAULT 'NEW' | Állapot |
| `latitude` | DECIMAL(9,6) | NOT NULL | GPS szélesség |
| `longitude` | DECIMAL(9,6) | NOT NULL | GPS hosszúság |
| `address` | VARCHAR(500) | | Szöveges cím (reverse geocode) |
| `created_at` | TIMESTAMP | NOT NULL, DEFAULT NOW() | Bejelentés ideje |
| `updated_at` | TIMESTAMP | NOT NULL, DEFAULT NOW() | Utolsó módosítás |

---

### `report_photos`

| Oszlop | Típus | Megkötés | Leírás |
|--------|-------|----------|--------|
| `id` | UUID | PK, NOT NULL | Egyedi azonosító |
| `report_id` | UUID | FK → reports.id, NOT NULL | Melyik bejelentéshez |
| `url` | VARCHAR(500) | NOT NULL | Fájl URL (S3 / helyi storage) |
| `uploaded_at` | TIMESTAMP | NOT NULL, DEFAULT NOW() | Feltöltés ideje |

---

### `categories`

| Oszlop | Típus | Megkötés | Leírás |
|--------|-------|----------|--------|
| `id` | SERIAL | PK | Azonosító |
| `name` | VARCHAR(100) | UNIQUE, NOT NULL | Kategória neve |
| `description` | TEXT | | Leírás |

---

## 2. Kapcsolatok (ER összefoglalás)

```
users 1 ──────── N reports
reports 1 ─────── N report_photos
categories 1 ──── N reports
```

- Egy felhasználó több bejelentést tehet
- Egy bejelentéshez több fotó tartozhat
- Minden bejelentés egy kategóriába esik

---

## 3. Indexek

```sql
CREATE INDEX idx_reports_user_id ON reports(user_id);
CREATE INDEX idx_reports_status ON reports(status);
CREATE INDEX idx_reports_created_at ON reports(created_at DESC);
CREATE INDEX idx_report_photos_report_id ON report_photos(report_id);
```

**Miért?** A leggyakoribb lekérdezések: "egy felhasználó bejelentései", "státusz szerinti szűrés", "legújabb bejelentések". Index nélkül ezek teljes tábla-scannel futnának.

---

## 4. Migrációk

A séma változásait **soha nem kézzel**, hanem migrációs eszközzel kezeld:
- **Flyway** (Spring Boot-hoz ajánlott): `src/main/resources/db/migration/V1__init.sql`
- **Liquibase** (alternatíva, XML/YAML alapú)

Minden séma módosítás = új migrációs fájl. Így a séma verziókövethető és visszaállítható.
