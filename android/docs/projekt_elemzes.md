---
title: "Közterület Felügyeleti App — Projekt Átfogó Elemzése"
author: "Szakdolgozat elemzés"
date: "2026-03-20"
geometry: "margin=2.5cm"
fontsize: 11pt
mainfont: "Helvetica"
sansfont: "Helvetica"
monofont: "Menlo"
header-includes:
  - \usepackage{fancyhdr}
  - \pagestyle{fancy}
  - \fancyhead[L]{Közterület Felügyeleti App}
  - \fancyhead[R]{Projekt Elemzés}
  - \fancyfoot[C]{\thepage}
  - \usepackage{xcolor}
  - \definecolor{codebg}{RGB}{248,248,248}
  - \usepackage{listings}
toc: true
toc-depth: 3
---

\newpage

# 1. A Projekt Jelenlegi Állapota

**Egy mondat:** Ez egy Android Studio által generált üres sablon projekt, nulla üzleti logikával — a "Hello World" szinten áll.

Ez nem kritika, ez az induló pont. Minden professzionális app innen indul. A projekt neve `kozteruletfelugyeletiapp`, célja egy közterület-felügyeleti mobilalkalmazás elkészítése, amely szakdolgozatként kerül benyújtásra a Debreceni Egyetem Informatikai Karán.

---

# 2. A Mappastruktúra — Mit Jelent Minden?

```
kozteruletfelugyeletiapp/
│
├── .gradle/              ← Gradle build cache, NE nyúlj hozzá
├── .idea/                ← Android Studio IDE beállítások, NE commitold
│
├── gradle/
│   ├── wrapper/
│   │   └── gradle-wrapper.properties   ← Gradle verzió (9.3.1)
│   ├── libs.versions.toml              ← Függőségek centralizált verziói
│   └── gradle-daemon-jvm.properties    ← JVM toolchain (Java 21)
│
├── app/                  ← A tényleges alkalmazás modul
│   ├── src/
│   │   ├── main/
│   │   │   ├── AndroidManifest.xml     ← Az app "személyigazolványa"
│   │   │   ├── java/…/MainActivity.java← Belépési pont
│   │   │   └── res/                    ← Vizuális erőforrások
│   │   │       ├── layout/             ← XML képernyő-elrendezések
│   │   │       ├── values/             ← Színek, szövegek, témák
│   │   │       ├── values-night/       ← Sötét mód témák
│   │   │       ├── drawable/           ← Vektorgrafika, ikonok
│   │   │       ├── mipmap-*/           ← App ikon különböző felbontásokhoz
│   │   │       └── xml/                ← Backup/adatvédelmi konfigok
│   │   ├── test/         ← Unit tesztek (JVM-en fut, emulator nélkül)
│   │   └── androidTest/  ← Instrumented tesztek (valódi eszközön fut)
│   ├── build.gradle.kts  ← App szintű build konfiguráció
│   └── proguard-rules.pro← Kód obfuszkáció release buildhez
│
├── build.gradle.kts      ← Root build script (plugin regisztráció)
├── settings.gradle.kts   ← Projekt neve, modulok listája
├── gradle.properties     ← JVM memória, build opciók
├── gradlew / gradlew.bat ← Gradle wrapper scriptek (cross-platform)
└── local.properties      ← Lokális Android SDK útvonal (NE commitold!)
```

---

# 3. Fájlonként — Mi Mire Való és Miért?

## `settings.gradle.kts` — A projekt neve és moduljai

```kotlin
rootProject.name = "kozterulet-felugyeleti-app"
include(":app")
```

Ez mondja meg Gradle-nek, hogy mi a projekt neve és milyen almodulok léteznek. Ha később külön `backend` vagy `shared` modult adsz hozzá, ide kell regisztrálni.

---

## `gradle/libs.versions.toml` — Verziókezelés centralizáltan

Ez a modern módszer. Ahelyett, hogy minden `build.gradle`-ben külön írnád a verziószámot, itt egy helyen tárolod:

```toml
[versions]
appcompat = "1.6.1"
material = "1.10.0"

[libraries]
androidx-appcompat = { group = "androidx.appcompat", name = "appcompat", version.ref = "appcompat" }
```

**Miért fontos?** Ha frissíteni kell egy könyvtárat, csak egy helyen kell módosítani. Ez a **DRY** (Don't Repeat Yourself) elv a gyakorlatban.

---

## `app/build.gradle.kts` — Az app build konfigurációja

```kotlin
compileSdk = 36      // Ezzel fordítod le (legfrissebb API-k elérhetők)
minSdk = 24          // Android 7.0 - lefedi a piac ~95%-át
targetSdk = 36       // Ezt a verziót "célozod" (viselkedési kompatibilitás)
```

A három SDK érték különbsége kritikus fogalom, amit a szakdolgozatban ki kell fejteni:

- **`compileSdk`**: fejlesztői eszközkészlet verziója
- **`minSdk`**: legalacsonyabb támogatott Android verzió
- **`targetSdk`**: amelyikre optimalizáltál (Google Play elvárja a frissítést)

---

## `AndroidManifest.xml` — Az app "útlevele"

Ez mondja meg az Android rendszernek:

- Mi az app neve, ikonja, témája
- Milyen `Activity`-k léteznek (képernyők)
- Milyen **engedélyeket** kér (INTERNET, CAMERA, LOCATION stb.)
- Melyik Activity nyílik meg indításkor (`LAUNCHER` intent)

**Ami majd kelleni fog a projekthez:**

```xml
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
<uses-permission android:name="android.permission.CAMERA"/>
```

---

## `MainActivity.java` — Az egyetlen képernyő jelenleg

```java
EdgeToEdge.enable(this);  // Teljes képernyős layout (státuszsor mögé nyúl)
setContentView(R.layout.activity_main);  // Betölti az XML layoutot
```

Az `EdgeToEdge` a 2024-2025-ös modern Android design alapköve — a tartalom a státuszsor és a navigációs sáv mögé nyúlik, te kezeled az inset-eket. Ezt kell majd minden képernyőnél alkalmazni.

---

## `res/values/themes.xml` + `res/values-night/themes.xml`

```xml
<style name="Base.Theme.KozteruletfelugyelApp"
       parent="Theme.Material3.DayNight.NoActionBar">
```

**Material Design 3** (`Material You`) — a Google 2021-től bevezetett design rendszere. A `DayNight` automatikusan vált világos/sötét mód között, a `NoActionBar` pedig eltávolítja a klasszikus felső sávot (helyette `Toolbar`-t vagy `TopAppBar`-t használsz).

---

## `res/values/colors.xml`

Jelenleg csak `#FF000000` (fekete) és `#FFFFFFFF` (fehér). Ez a következő teendők egyike — saját paletta definiálása.

---

## `proguard-rules.pro` — Release kód obfuszkáció

Produkciós build esetén a kódot "összezavarja" (rövidítés, átnevezés), hogy nehezebb legyen visszafejteni és kisebb legyen az APK. Jelenleg template kommentekkel teli, ténylegesen üres.

---

## Tesztelési fájlok

| Fájl | Típus | Fut |
|------|-------|-----|
| `ExampleUnitTest.java` | Unit teszt | JVM-en, emulator nélkül |
| `ExampleInstrumentedTest.java` | Instrumented teszt | Valódi Android eszközön |

---

# 4. A Tech Stack Mélyebb Megértése

## Build rendszer: Gradle

A Gradle egy build automatizálási eszköz. Feladata:

1. Függőségek letöltése (Maven Central, Google Maven)
2. A forráskód lefordítása
3. Erőforrások csomagolása
4. APK / AAB (Android App Bundle) létrehozása

A projekt Kotlin DSL-t (`.kts` kiterjesztés) használ a hagyományos Groovy helyett — ez típusbiztos, IDE-barát szintaxis.

## Java vs Kotlin

A projekt Java-t használ. Ez érvényes döntés, de **2025-ben a Google Kotlin-t ajánlja** elsődleges Android nyelvként:

- Kotlin tömörebb kódot eredményez
- Null safety beépítve (kevesebb NullPointerException)
- Coroutine-ok aszinkron feladatokhoz
- Extension functions, data classes

**Javaslat szakdolgozathoz:** Indokold meg a Java választást, vagy térj át Kotlin-ra. Mindkettő védhető döntés, de dokumentálni kell az indoklást.

## Material Design 3 — Főbb Komponensek

A Material Design 3 nem csak vizuális rendszer, hanem **komponenskönyvtár**:

| Komponens | Mire Való |
|-----------|-----------|
| `MaterialButton` | Lekerekített sarokú gombok |
| `TextInputLayout` | Lebegő label-es beviteli mezők |
| `BottomNavigationView` | Alsó navigációs sáv |
| `CardView` | Kártyák (lista elemekhez) |
| `FloatingActionButton` | Főakció gomb (pl. új riport) |
| `Snackbar` | Rövid értesítések |
| `NavigationDrawer` | Oldalsó menü |
| `TopAppBar` | Felső alkalmazássáv |

---

# 5. Saját Design Létrehozása és Integrálása

## A Material Theme Builder eszköz

A Google ingyenes online eszközével (Material Theme Builder) saját brand szín megadása alapján teljes palettát generálhatsz, amely exportálható közvetlenül Android XML formátumba.

## Hogyan integráld lépésről lépésre?

**1. lépés** — Definiálj színeket `colors.xml`-ben:

```xml
<color name="md_theme_primary">#FF6B35</color>
<color name="md_theme_on_primary">#FFFFFF</color>
<color name="md_theme_surface">#FFF8F6</color>
<color name="md_theme_background">#FDF8F3</color>
```

**2. lépés** — Alkalmazd a témában `themes.xml`-ben:

```xml
<style name="AppTheme" parent="Theme.Material3.DayNight.NoActionBar">
    <item name="colorPrimary">@color/md_theme_primary</item>
    <item name="colorOnPrimary">@color/md_theme_on_primary</item>
    <item name="colorSurface">@color/md_theme_surface</item>
    <item name="android:fontFamily">@font/inter</item>
</style>
```

**3. lépés** — Betűtípus integrálása (Google Fonts):

```xml
<!-- res/font/inter.xml -->
<font-family xmlns:app="http://schemas.android.com/apk/res-auto">
    <font app:fontStyle="normal" app:fontWeight="400" app:font="@font/inter_regular"/>
    <font app:fontStyle="normal" app:fontWeight="600" app:font="@font/inter_semibold"/>
</font-family>
```

**4. lépés** — Saját UI komponens (pl. lekerekített riport kártya):

```xml
<com.google.android.material.card.MaterialCardView
    app:cardCornerRadius="16dp"
    app:cardElevation="2dp"
    app:strokeWidth="0dp">
    ...
</com.google.android.material.card.MaterialCardView>
```

## Javasolt "Meleg + Minimalista" Paletta

```xml
<color name="warm_primary">#E07B54</color>      <!-- Terrakotta narancs -->
<color name="warm_secondary">#C4956A</color>    <!-- Meleg bézs-barna -->
<color name="warm_background">#FDF8F3</color>   <!-- Törtfehér -->
<color name="warm_surface">#FFFFFF</color>
<color name="warm_on_primary">#FFFFFF</color>
<color name="warm_text_primary">#1C1B1F</color>
<color name="warm_text_secondary">#49454F</color>
```

---

# 6. Architektúra — Hogyan Épüljön Fel a Végső App?

A jelenlegi állapot: **1 Activity, 0 logika.** A végső, szakdolgozati szintű architektúra:

## Clean Architecture + MVVM

```
Presentation Layer (UI)
├── Activities / Fragments
├── ViewModels (state kezelés, UI logika)
└── UI State osztályok

Domain Layer (üzleti logika)
├── Use Cases (pl. SubmitReportUseCase)
├── Domain Models
└── Repository Interface-ek (absztrakció)

Data Layer (adatkezelés)
├── Repository Implementációk
├── Remote Data Source (Retrofit / Firebase)
└── Local Data Source (Room database)
```

Ez az architektúra biztosítja:

- **Tesztelhetőség**: minden réteg önállóan tesztelhető
- **Karbantarthatóság**: a változások nem gyűrűznek szét
- **Separation of Concerns**: minden rétegnek egyetlen felelőssége van

## Navigáció (Jetpack Navigation Component)

```
MainActivity (host)
└── NavHostFragment
    ├── LoginFragment
    ├── RegisterFragment
    ├── HomeFragment (riportok listája)
    ├── ReportDetailFragment
    └── NewReportFragment
```

---

# 7. A Szakdolgozat Dokumentálása

## Fejlesztési Napló Formátuma

Minden sprint / fejlesztési fázis után a `docs/changelog.md` fájlba:

```markdown
## 2026-03-17 — Sprint 1: Alapok lerakása

### Elvégzett feladatok
- Projekt inicializálás Android Studio-ban
- Gradle verziókezelés beállítása
- Material Design 3 téma alapok

### Technikai döntések és indoklásuk
- Java vs Kotlin: Java-t választottam, mert...
- minSdk = 24: Az Android 7.0 lefedi a piac 95%-át...

### Problémák és megoldásuk
- Gradle sync hiba -> Megoldás: ...

### Következő feladatok
- Autentikációs képernyő tervezése
```

## Mit Gyűjts Most Már a Dolgozathoz?

| Szakasz | Mit Dokumentálj Most |
|---------|----------------------|
| Irodalomkutatás | Meglévő közterület-felügyeleti appok elemzése |
| Technológia indoklás | Miért Android, miért Material Design 3 |
| Architektúra | Az MVVM + Clean Architecture döntés indoklása |
| Design | Paletta, tipográfia, komponensek leírása |
| Adatmodell | User, Report, Location, Photo entitások |

---

# 8. DEIK Szakdolgozat Felépítése Mobilalkalmazás Témában

## Tipikus Fejezetek

### 1. Bevezetés

- A téma motivációja
- A probléma bemutatása (miért kell egy közterület-felügyeleti app?)
- Célkitűzések
- A dolgozat felépítése

### 2. Irodalomkutatás / Kapcsolódó munkák

- Meglévő hasonló alkalmazások elemzése (Fix My Street, 311 apps stb.)
- Mobilfejlesztési platformok összehasonlítása (Android vs iOS vs cross-platform)
- Felhasznált technológiák bemutatása (Android SDK, Material Design, Firebase/Spring Boot)

### 3. Követelményanalízis

- Funkcionális követelmények (mit tud az app)
- Nem-funkcionális követelmények (teljesítmény, biztonság, használhatóság)
- Use Case diagramok
- User Story-k

### 4. Tervezés

- Rendszerarchitektúra (komponens diagram)
- Adatmodell (ER diagram, osztálydiagram)
- UI/UX tervek (wireframe-ek, mockup-ok — Figma)
- Adatbázis séma

### 5. Implementáció

- Fejlesztői környezet bemutatása
- Főbb komponensek implementálása (autentikáció, riport funkció, listázás)
- Kiemelkedő kódrészletek magyarázata
- Design rendszer bemutatása

### 6. Tesztelés

- Tesztelési stratégia
- Unit tesztek (JUnit)
- UI tesztek (Espresso)
- Manuális tesztelés eredményei

### 7. Összefoglalás

- Elért eredmények
- Jövőbeni fejlesztési lehetőségek

### Mellékletek

- Telepítési útmutató
- Forráskód részletek
- Képernyőképek

---

# 9. Összefoglaló: Jelenlegi Állapot és Teendők

## Projekt Állapot Összefoglalás

| Terület | Állapot |
|---------|---------|
| Projekt scaffold | Kész |
| Build konfiguráció | Kész |
| Material Design 3 alap | Kész |
| Sötét/Világos mód | Alap kész |
| Tesztelési infrastruktúra | Alap kész |
| Üzleti logika | Nincs |
| Autentikáció | Nincs |
| Navigáció | Nincs |
| Adatréteg | Nincs |
| Saját design / paletta | Nincs |

## Ajánlott Következő Lépések

**Rövid táv:**

1. Definiálj adatmodellt (User, Report, Location, Photo)
2. Tervezz wireframe-eket Figmában
3. Döntsd el: Firebase (gyorsabb MVP) vagy Spring Boot (komolyabb backend)
4. Fontold meg a Kotlin-ra való átállást

**Közép táv:**

1. Navigáció (Jetpack Navigation Component)
2. Autentikáció (Firebase Auth vagy JWT)
3. Riport képernyő + kamera integráció
4. Lista képernyő + térkép nézet

**Dokumentáció folyamata:**

- Minden döntés -> `docs/changelog.md`
- Minden képernyő kész -> képernyőkép a `docs/screenshots/` mappába
- Minden architektúrális döntés -> `docs/architecture.md`

---

*Generálva: 2026-03-20 | Projekt: Közterület Felügyeleti App | Debreceni Egyetem Informatikai Kar*
