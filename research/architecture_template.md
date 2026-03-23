# ARCHITECTURE — [Projekt neve]

> Ez egy template fiktív projekttel illusztrálva.
> Másold át az architecture.md fájlba és töltsd ki a saját projektedre.

---

## 1. Rendszeráttekintés

A rendszer három fő komponensből áll:

```
[Android App]  ──────┐
                     ├──► [Spring Boot REST API] ──► [PostgreSQL]
[Webadmin (React)] ──┘
```

Minden kliens (Android, webadmin) kizárólag a REST API-n keresztül kommunikál az adatbázissal. Közvetlen DB hozzáférés nincs kliens oldalról.

---

## 2. Komponensek

### 2.1 Android alkalmazás

**Technológia:** Kotlin, Jetpack Compose, Retrofit, Hilt

**Rétegek (Clean Architecture + MVVM):**

```
app/
├── ui/                  ← Compose képernyők, ViewModelek
│   ├── auth/
│   ├── reports/
│   └── profile/
├── domain/              ← Use case-ek, domain modellek, repository interfészek
│   ├── model/
│   ├── usecase/
│   └── repository/
└── data/                ← Repository implementációk, API hívások, helyi cache
    ├── remote/          ← Retrofit service-ek, DTO-k
    └── local/           ← Room (opcionális offline cache)
```

**Kommunikáció:** HTTPS REST API → JWT token minden kérésben `Authorization: Bearer <token>` headerben.

---

### 2.2 Backend REST API

**Technológia:** Spring Boot 3, Spring Security, JPA/Hibernate

**Rétegek:**

```
src/main/java/
├── controller/          ← HTTP endpoint-ok (AuthController, ReportController)
├── service/             ← Üzleti logika
├── repository/          ← JPA repository interfészek
├── model/               ← JPA entitások
├── dto/                 ← Request/Response objektumok
└── security/            ← JWT filter, UserDetailsService
```

**Főbb endpoint-ok:**

| Metódus | Útvonal | Leírás | Jogosultság |
|---------|---------|--------|-------------|
| POST | `/api/auth/register` | Regisztráció | Publikus |
| POST | `/api/auth/login` | Bejelentkezés | Publikus |
| GET | `/api/reports` | Bejelentések listája | USER, ADMIN |
| POST | `/api/reports` | Új bejelentés | USER |
| PATCH | `/api/reports/{id}/status` | Státusz módosítás | ADMIN |
| GET | `/api/admin/users` | Felhasználók listája | ADMIN |

---

### 2.3 Webadmin

**Technológia:** React 18, TypeScript, shadcn/ui, React Query

**Struktúra:**

```
src/
├── pages/               ← Oldalak (Dashboard, Reports, Users)
├── components/          ← Újrafelhasználható UI elemek
├── api/                 ← API hívások (axios kliensek)
├── hooks/               ← Custom React hook-ok
└── types/               ← TypeScript típusdefiníciók
```

---

### 2.4 Adatbázis

**Technológia:** PostgreSQL 16

Részletes séma: `research/data_schema.md`

---

## 3. Autentikáció folyamata

```
1. Kliens: POST /api/auth/login { email, password }
2. Szerver: ellenőrzi, generál JWT access token + refresh token
3. Kliens: eltárolja a tokent (Android: EncryptedSharedPreferences)
4. Kliens: minden kéréshez csatolja: Authorization: Bearer <token>
5. Szerver: JWT filter validálja a tokent minden védett endpoint előtt
```

---

## 4. Biztonsági megfontolások

- Jelszavak: BCrypt hash tárolás, soha nem plain text
- Token: RS256 algoritmus, rövid lejárat (15 perc access, 7 nap refresh)
- HTTPS: minden kommunikáció titkosított
- Input validáció: szerver oldalon minden bemeneti adat validálva
- CORS: csak az ismert origin-ek engedélyezve

---

## 5. Deployment

| Komponens | Platform | Megjegyzés |
|-----------|----------|------------|
| REST API | Render (free) | Docker container |
| PostgreSQL | Render Managed DB | 1 GB free tier |
| Webadmin | Vercel (free) | Statikus build |
| Android | APK megosztás | Google Play opcionálisan |
