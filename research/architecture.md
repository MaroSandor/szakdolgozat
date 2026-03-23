# ARCHITECTURE - Közterület felügyelet alkalmazás Android platformon

## 1. Rendszeráttekintés

A rendszer 3 fő komponense:

```
[ Mobile App ] ───────┐
                      ├──► [ Spring Boot REST API ] ──► [ PostgreSQL ]
[ WebAdmin (React) ] ─┘
```

Minden kliens (Android és webapp) csak a **REST API-n** keresztül képes kommunikálni az adatbázissal, **közvetlen hozzáférésük** az adatbázishoz a klienseknek **nincs**!

## 2. Komponensek

### 2.1 Android alkalmazás (Java + Jetpack Compose)

**Architektúra:**

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

Az Android alkalmazás a projekt fő eszköze. Ezen keresztül tudnak a felhasználók jelentéseket beküldeni.

Az alkalmazás UI felületéről a docs/android-ui.md fájlban olvashatsz.

### 2.2 Backend REST API (Java + Spring Boot)



### 2.3 Webadmin (React + React Router + JWT Auth)



### 2.4 Adatbázis (PostgreSQL)

## 3. Autentikáció folyamata

## 4. Adatbiztonság

- Jelszóvédelem: tárolás bcrypt hash-sel
- JWT token: token alapú hozzáférés rövid lejárati idővel (10-15 perc, 7 napos újragenerálás)
- HTTPS: minden végpontra
- Validáció: minden validáció szerver oldalon
- CORS: csak ismert origin-ek

## 5. Deployment

| Komponens | Platform | Megjegyzés |
|-----|-----|-----|
| Backend (REST API) | Render (free) | Docker konténer |
| PostgreSQL | Render Managed DB | 1GB ingyenes csomag |
| Webapp | Vercel (ingyenes verzió) | Statikus build kiszolgálás |
| Android | APK megosztás felhőtárhelyen | Google Play (opcionális, fizetős megoldás) |
| iOS | Kizárólag App Store (fizetős) | - |