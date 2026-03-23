# REST API (Backend) — TODO lista

> Utoljára frissítve: 2026-03-23

---

## Technológiai döntések
- [ ] Backend stack: Spring Boot + PostgreSQL vs Firebase vs Supabase
- [ ] REST API vs GraphQL kommunikáció
- [ ] Autentikáció módszere: JWT, OAuth2 vagy Firebase Auth
- [ ] Infrastruktúra / hosting döntés (Cloudflare, Railway, Render, stb.)

---

## Adatbázis tervezés
- [ ] Entitások azonosítása (User, Report, Category, Status, Photo, stb.)
- [ ] ER diagram elkészítése
- [ ] Táblák és kapcsolatok megtervezése
- [ ] Adatbázis séma dokumentálása (`docs/database_schema.md`)

---

## Implementáció
- [ ] Projekt scaffold (Spring Initializr vagy Firebase projekt)
- [ ] Adatbázis kapcsolat + migráció (Flyway / Liquibase)
- [ ] Autentikáció endpoint-ok (register, login, refresh token)
- [ ] Report CRUD endpoint-ok
- [ ] Felhasználókezelés endpoint-ok (admin)
- [ ] Fájlfeltöltés (képek tárolása)
- [ ] Jogosultságkezelés (USER / ADMIN szerepkörök)

---

## Biztonság
- [ ] HTTPS kényszerítés
- [ ] Input validáció minden endpoint-on
- [ ] Rate limiting
- [ ] .env alapú konfigurációkezelés (soha nem hardcoded secrets!)

---

## Dokumentáció
- [ ] `docs/database_schema.md` elkészítése
- [ ] API endpoint dokumentáció (Swagger / OpenAPI)
