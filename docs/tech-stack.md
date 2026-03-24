# Tech Stack

> Utoljára frissítve: 2026-03-23

Ez a dokumentum összefoglalja az alkalmazás minden komponenséhez választott technológiákat és az azok mögötti döntési szempontokat.

---

## Tartalomjegyzék

1. [Android alkalmazás](#android-alkalmazas)
2. [iOS alkalmazás](#ios-alkalmazas)
3. [Backend — REST API](#backend--rest-api)
4. [Webalkalmazás — Admin felület](#webalkalmazas--admin-felulet)
5. [Infrastruktúra](#infrastruktura)
6. [Összefoglalás](#osszefoglalas)

---

## Android alkalmazás

### Alapok

| Kategória | Technológia | Verzió |
|---|---|---|
| Nyelv | Kotlin | 2.x |
| Min SDK | Android 8.0 (API 26) | — |
| Target SDK | Android 15 (API 36) | — |
| Build rendszer | Gradle (Kotlin DSL) | 9.x |

**Miért Kotlin?**
A Google 2019 óta Kotlin-first stratégiát követ. Az összes modern Jetpack könyvtár Kotlinra van optimalizálva. A Java alternatívával számos modern API (Coroutines, Flow, Compose) csak nehézkesen vagy egyáltalán nem használható.

### UI

| Kategória | Technológia |
|---|---|
| UI framework | Jetpack Compose |
| Téma rendszer | Material Design 3 |
| Navigáció | Compose Navigation (type-safe routes) |
| Kép betöltés | Coil |
| Térkép | Google Maps SDK for Android |
| Kamera | CameraX |

**Miért Jetpack Compose?**
Deklaratív UI paradigma — hasonló a SwiftUI-hoz és a React-hoz. 2025-ben ez az Android fejlesztés de facto standardja. Az XML alapú View rendszer örökségnek számít, új projektekbe nem érdemes belekezdeni.

### Architektúra

| Kategória | Technológia |
|---|---|
| Mintázat | Clean Architecture + MVVM |
| ViewModel | Jetpack ViewModel + StateFlow |
| Dependency Injection | Hilt (Dagger alapú) |
| Async | Kotlin Coroutines + Flow |
| Lokal adattárolás | EncryptedSharedPreferences (token) |
| Offline cache (opcionális) | Room (SQLite wrapper) |

**Rétegek:**
```
presentation/  →  domain/  ←  data/
```
- A `domain` réteg pure Kotlin, semmitől nem függ
- A `data` réteg implementálja a `domain` interfészeket
- A `presentation` réteg csak use case-eket hív

### Hálózat

| Kategória | Technológia |
|---|---|
| HTTP kliens | Retrofit 2 + OkHttp 4 |
| JSON | Gson vagy Moshi |
| Auth | JWT Bearer token (EncryptedSharedPreferences-ből) |

### Tesztelés

| Típus | Technológia |
|---|---|
| Unit tesztek | JUnit 5 + Mockito |
| UI tesztek | Compose Testing |
| Integrációs | AndroidX Test |

---

## iOS alkalmazás

> Státusz: opcionális — az Android MVP után kerül sorra.

### Alapok

| Kategória | Technológia |
|---|---|
| Nyelv | Swift 6 |
| Min verzió | iOS 17 |
| UI framework | SwiftUI |

### Architektúra

| Kategória | Technológia |
|---|---|
| Mintázat | MVVM |
| Async | Swift Concurrency (async/await + Actor) |
| DI | Manuális constructor injection |
| HTTP | URLSession (natív) |
| Auth token | Keychain |
| Térkép | MapKit (natív, SwiftUI-integráció) |
| Kamera | AVFoundation |

**Miért nincs külső DI framework iOS-en?**
Az iOS ökoszisztémában nincs Hilt-equivalent, ami széleskörűen elfogadott lenne. A constructor injection + protocol-alapú absztrakció elegendő, és tesztelhető marad.

---

## Backend — REST API

### Két opció

A backend technológia megválasztása a szakdolgozat fókuszától függ:

| Szempont | Spring Boot + PostgreSQL | Supabase (BaaS) |
|---|---|---|
| Fejlesztési idő | Hosszabb | Rövidebb |
| Kontroll | Teljes | Korlátozott |
| Szakdolgozatban bemutatható mélység | Magas | Közepes |
| Infrastruktúra igény | Igen (Docker/VPS) | Nem |
| Vendor lock-in | Nincs | Van |
| SQL tudás demonstrálása | Igen | Igen (PostgreSQL) |

**Ajánlás:** Ha a backend is hangsúlyos témakör a szakdolgozatban → Spring Boot. Ha az MVP gyorsasága és a mobilfejlesztés a fő fókusz → Supabase.

---

### A opció — Spring Boot + PostgreSQL

#### Alapok

| Kategória | Technológia | Verzió |
|---|---|---|
| Nyelv | Kotlin | 2.x |
| Framework | Spring Boot | 3.x |
| Java verzió | JDK 21 (LTS) | — |
| Adatbázis | PostgreSQL | 16 |
| Build | Gradle (Kotlin DSL) | — |

#### Rétegek

| Kategória | Technológia |
|---|---|
| ORM | Spring Data JPA + Hibernate |
| Migráció | Flyway |
| Auth | Spring Security + JWT (jjwt) |
| Jelszó hash | BCrypt |
| API dokumentáció | SpringDoc OpenAPI (Swagger UI) |
| Validáció | Jakarta Bean Validation |
| File feltöltés | Cloudflare R2 (S3-kompatibilis) |

#### Architektúra

```
Controller  →  Service  →  Repository  →  Database
                 ↕
              Domain modell
```

- `Controller`: HTTP réteg, request/response DTO-k
- `Service`: üzleti logika, tranzakció kezelés
- `Repository`: adatbázis műveletek (Spring Data JPA interface-ek)
- `Domain`: entitások és értékobjektumok

#### Deployment

| Kategória | Technológia |
|---|---|
| Konténerizáció | Docker + Docker Compose |
| Hosting | Railway, Render vagy VPS (Hetzner) |
| Környezeti változók | `.env` fájl (soha nem kerül verziókövetésbe) |

---

### B opció — Supabase (BaaS)

| Szolgáltatás | Technológia |
|---|---|
| Adatbázis | PostgreSQL (Supabase managed) |
| Auth | Supabase Auth (JWT, OAuth támogatás) |
| REST API | PostgREST (automatikus, sémából generált) |
| File tárolás | Supabase Storage |
| Backend logika | Edge Functions (Deno/TypeScript) |
| Valós idejű | Supabase Realtime (WebSocket) |

**Integráció mobilon:**
- Android: `supabase-kt` (Kotlin multiplatform kliens)
- iOS: `supabase-swift`
- Web: `@supabase/supabase-js`

---

## Webalkalmazás — Admin felület

### Alapok

| Kategória | Technológia | Verzió |
|---|---|---|
| Framework | Next.js | 15.x |
| Nyelv | TypeScript | 5.x |
| React | React | 19.x |
| Csomag kezelő | pnpm | 9.x |

**Miért Next.js és nem sima React (Vite/CRA)?**
Az admin felület szerver-oldali adatigényeket is tartalmaz. A Next.js App Router SSR-t, API route-okat és egyszerű Vercel deploy-t biztosít — ezeket sima React-tal manuálisan kellene megvalósítani.

### UI

| Kategória | Technológia |
|---|---|
| CSS framework | Tailwind CSS v4 |
| Komponens könyvtár | shadcn/ui |
| Ikonok | Lucide React |
| Térkép | Leaflet + React-Leaflet |
| Táblázatok | TanStack Table |
| Grafikonok | Recharts |

**Miért shadcn/ui?**
Nem egy hagyományos npm csomag — a komponensek a saját kódod részévé válnak, teljesen testreszabhatók. Tailwind-alapú, így konzisztens a stílusrendszerrel.

### Állapotkezelés és adat

| Kategória | Technológia |
|---|---|
| Szerver state | TanStack Query (React Query) |
| Globális state | Zustand |
| HTTP kliens | Axios |
| Form kezelés | React Hook Form |
| Validáció | Zod |

**TanStack Query szerepe:**
Cache-eli a szerver válaszokat, kezeli a loading/error állapotokat, automatikus újratöltés. Az admin felület adatainak 90%-a szerverről jön, ezért ez fontosabb mint egy Redux-szerű megoldás.

### Auth

| Backend | Auth megoldás |
|---|---|
| Spring Boot | NextAuth.js (JWT stratégia) |
| Supabase | Supabase Auth + `@supabase/ssr` |

### Tesztelés

| Típus | Technológia |
|---|---|
| Unit / Komponens | Vitest + React Testing Library |
| E2E | Playwright |

### Deployment

| Kategória | Technológia |
|---|---|
| Hosting | Vercel (Next.js natív) |
| Környezeti változók | `.env.local` (soha nem kerül verziókövetésbe) |

---

## Infrastruktúra

| Szolgáltatás | Technológia | Megjegyzés |
|---|---|---|
| DNS + CDN | Cloudflare | Ingyenes tier elegendő |
| File tárolás | Cloudflare R2 | S3-kompatibilis, ingress díj nincs |
| Backend hosting | Railway / Render | Spring Boot Docker image |
| Web hosting | Vercel | Next.js deploy automatikus |
| Adatbázis | Railway PostgreSQL | Vagy Supabase managed |
| Titkok kezelése | `.env` fájlok | Soha nem commitolni! |

---

## Összefoglalás

```
+------------------+----------------------------------------+
| Komponens        | Stack                                  |
+------------------+----------------------------------------+
| Android          | Kotlin + Jetpack Compose + Hilt        |
|                  | + Retrofit + Coroutines + Flow         |
+------------------+----------------------------------------+
| iOS (opcionális) | Swift + SwiftUI + URLSession           |
|                  | + Swift Concurrency                    |
+------------------+----------------------------------------+
| Backend          | Spring Boot (Kotlin) + PostgreSQL      |
|                  | + JWT + Flyway + Docker                |
|                  | --- VAGY ---                           |
|                  | Supabase (PostgreSQL + Auth + Storage) |
+------------------+----------------------------------------+
| Webalkalmazás    | Next.js 15 + TypeScript + Tailwind     |
|                  | + shadcn/ui + TanStack Query + Zod     |
+------------------+----------------------------------------+
| Infrastruktúra   | Cloudflare + Vercel + Railway          |
+------------------+----------------------------------------+
```

---

> **Nyitott döntések:**
> - [ ] Spring Boot vs. Supabase backend veglegesitese
> - [ ] iOS scope pontositasa (MVP-be belefer-e?)
