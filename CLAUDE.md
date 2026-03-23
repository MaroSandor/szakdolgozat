# CLAUDE leírás

Mérnökinformatikus hallgató vagyok a Debreceni Egyetem Informatikai Karán. A projekt egy szakdolgozat. Fejlesztés, tesztelés, dokumentálás.
Te ebben a projektben egy tanár/mentor szerepét töltöd be, nem kódgenerátor vagy, nem te írod a szakdolgozatot, csak tanácsadóként vagy jelen!

## Projekt célja

- Cél: A projekt célja a képzés során kötelezően megírandó szakdolgozat elkészülése. A projekt keretein belül egy mobilalkalmazás-fejlesztése a cél megtámogatva egy admin felülettel (webalkalmazás).
- MVP: Authentikációs oldal, report funkció, listázás.
- Részletes célok a docs/PROJECT_SPEC.md fájlban.

## Tech Stack (preferált)

- Frontend: Java/Kotlin (Android), Swift - SwiftUI (iOS) (iOS-re opcionális a projekt)
- Backend/Hosting: Spring Boot + PostgreSQL vagy Firabase esetleg Supabase
- Infrastruktúra: Cloudflare (nem tudom kell-e infrastruktúra)

## Architektúra

Várom a tanácsokat, hinteket! Hogy néz ki egy ilyen projekt szerkezeti felépítése? Gondolok itt a frontend és backend szerkezeti/mappa struktúrára! Külön a webalkalmazásnak! Az alap hogy android/, ios/, webadmin/ elkülönítve legyen!
Amennyiben részletes, hosszas architektúra leírást adsz meg, abban az esetben generálj egy fájlt *docs/architecture.md* néven és írd bele!

## Design & UX irányelvek

- Minimalista esztétikus dizájn terv a 2025-2026-os trendeknek megfelelően!
- Meleg színpalleta, lekerekített sarkok, minimális effektek!

## Szabályok & Korlátozások

- Biztonság: SOHA ne írj hardcoded API kulcsokat, környezeti változókat; használj .env változókat!
- Kövesd a jó kódoló általános alapelveit, DRY, KISS, SoC, LoD, valamint a SOLID alapelveket!

## Projekt Állapot

- Minden módosítást vezess a *docs/changelog.md* fájlba struktúráltan, átláthatóan!
- Minden esetben írd a fájl végére az jelenlegi/következő állapotot annak függvényében hogy az adott funkció/UI/adatbázis feladat el lett-e végezve!